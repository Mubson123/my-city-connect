package com.mc.extend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.extend.fixtures.VisaFixtures;
import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@EmbeddedKafka(partitions = 1, topics = {"kafka.topic.extend-residence-permit-events"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VisaControllerIT {
    static final String BASE_PATH = "/api/v2/residence-permit/visas";
    static String id;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(10)
    void shouldCreateVisaSuccessfully() throws Exception {
        ApiVisaRequest visaRequest = VisaFixtures.request2;
        var response = mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visaRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.visaNumber").value(visaRequest.getVisaNumber()))
                .andReturn()
                .getResponse();
        ApiVisaResponse apiResponse = objectMapper.readValue(response.getContentAsString(), ApiVisaResponse.class);
        id = String.valueOf(apiResponse.getId());
    }

    @Test
    @Order(12)
    void shouldCreateVisaException() throws Exception {
        ApiVisaRequest visaRequest = VisaFixtures.badRequest;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visaRequest)))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    @Order(20)
    void shouldGetVisaById() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.countryOfIssue").value("Germany"));
    }

    @Test
    @Order(24)
    void shouldGetVisaByIdNotFound() throws Exception {
        String randomUUID = String.valueOf(UUID.randomUUID());
        mockMvc.perform(get(BASE_PATH + "/{id}", randomUUID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.error").value("Resource not found"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(30)
    void shouldUpdateVisa() throws Exception {
        ApiVisaRequest visaRequest = VisaFixtures.request2Updated;
        ApiVisaResponse visaResponseUpdated = VisaFixtures.response2Updated;
        ApiVisaResponse visaResponse = VisaFixtures.response2;
        mockMvc.perform(put(BASE_PATH + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visaRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.countryOfIssue").value(visaRequest.getCountryOfIssue()));
        assertThat(visaResponseUpdated).isNotEqualTo(visaResponse);
    }

    @Test
    @Order(40)
    void shouldDeleteVisa() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
