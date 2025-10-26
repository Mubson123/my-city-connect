package com.mc.citizen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.citizen.fixtures.CitizenFixtures;
import com.mc.citizen.model.ApiCitizenRequest;
import com.mc.citizen.model.ApiCitizenResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CitizenControllerIT {
    static final String BASE_PATH = "/api/v2/registration/citizens";
    static String id;
    static String email;
    static String phone;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(10)
    void shouldCreateCitizen() throws Exception {
        ApiCitizenRequest citizenRequest = CitizenFixtures.request2;

        var response = mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(citizenRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(citizenRequest.getFirstName()))
                .andReturn()
                .getResponse();
        ApiCitizenResponse created = objectMapper.readValue(response.getContentAsString(), ApiCitizenResponse.class);
        id = String.valueOf(created.getId());
        email = created.getEmail();
        phone = created.getPhones().getFirst().getNumber();
    }

    @Test
    @Order(12)
    void shouldCreateCitizenException() throws Exception {
        ApiCitizenRequest wrongRequest = CitizenFixtures.wrongRequest;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(14)
    void shouldCreateCitizenEmailAlreadyExists() throws Exception {
        ApiCitizenRequest wrongRequest = CitizenFixtures.request2;
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongRequest)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("Email already exists"));
    }

    @Test
    @Order(20)
    void shouldGetCitizensByLimit() throws Exception {
        int limit = 2;

        mockMvc.perform(get(BASE_PATH)
                        .param("limit", String.valueOf(limit))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Maria"));
    }

    @Test
    @Order(22)
    void shouldGetCitizensByLimitFailed() throws Exception {
        int limit = -1;

        mockMvc.perform(get(BASE_PATH)
                        .param("limit", String.valueOf(limit))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.message").value("limit should be greater than 0"))
                .andExpect(jsonPath("$.error").value("Invalid request argument"));
    }

    @Test
    @Order(30)
    void shouldGetCitizensById() throws Exception {
        mockMvc.perform(get(BASE_PATH + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Maria"));
    }

    @Test
    @Order(32)
    void shouldGetCitizensByIdNotFound() throws Exception {
        String randomUUID = String.valueOf(UUID.randomUUID());
        mockMvc.perform(get(BASE_PATH + "/{id}", randomUUID)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.error").value("Resource not found"))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(40)
    void shouldUpdateCitizen() throws Exception {
        ApiCitizenRequest updatedCitizenRequest = CitizenFixtures.updatedRequest1;
        mockMvc.perform(put(BASE_PATH + "/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedCitizenRequest)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.email").value("dupont123jean@gmail.com"))
                .andExpect(jsonPath("$.phones[0].number").value("0176586224"));
        assertThat(email).isNotEqualTo(updatedCitizenRequest.getEmail());
        assertThat(phone).isNotEqualTo(updatedCitizenRequest.getPhones().getFirst().getNumber());
    }

    @Test
    @Order(50)
    void shouldDeleteCitizen() throws Exception {
        mockMvc.perform(delete(BASE_PATH + "/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
