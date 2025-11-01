package com.mc.extend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.extend.fixtures.VisaFixtures;
import com.mc.extend.model.ApiVisaRequest;
import com.mc.extend.model.ApiVisaResponse;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@EmbeddedKafka(partitions = 1, topics = {"visa-events"})
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
        ApiVisaRequest visaRequest = VisaFixtures.request1;
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
}
