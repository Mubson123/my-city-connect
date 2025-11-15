package com.mc.oauth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mc.oauth.dto.SignInRequest;
import com.mc.oauth.dto.SignUpRequest;
import com.mc.oauth.fixtures.AuthFixtures;
import com.mc.oauth.models.Role;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.models.User;
import com.mc.oauth.repository.UserRepository;
import com.mc.oauth.service.JwtService;
import com.mc.oauth.service.TokenService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthE2EITest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper json;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DataSource dataSource;

    @Test
    @Order(1)
    void contextLoads() {
        assertThat(mockMvc).isNotNull();
        assertThat(dataSource).isNotNull();
    }

    @Test
    @Order(2)
    void SignUpShouldCreateUserInDatabase() throws Exception {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String email = signUpRequest.getEmail();

        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("User registered successfully"));

        User user = userRepository.findByEmail(email).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getRole()).isEqualTo(Role.USER);
    }

    @Test
    @Order(3)
    void SignInShouldReturnValidTokens() throws Exception {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String email = signUpRequest.getEmail();

        // First, sign up the user
        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        // Then, sign in the user
        SignInRequest signInRequest = AuthFixtures.signInRequest;
        var response = mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
                .andReturn()
                .getResponse();

        String responseBody = response.getContentAsString();
        Tokens tokens = json.readValue(responseBody, Tokens.class);

        // Validate access token
        String accessToken = tokens.getAccessToken();
        String refreshToken = tokens.getRefreshToken();
        String tokenEmail = jwtService.extractEmail(accessToken);
        assertThat(tokenEmail).isEqualTo(email);
        assertThat(jwtService.validateToken(accessToken)).isTrue();
        assertThat(jwtService.isRefreshTokenExpired(refreshToken)).isFalse();

        var claim = jwtService.extractAllClaims(accessToken);
        assertThat(claim.getSubject()).isEqualTo(email);
    }

    @Test
    @Order(4)
    void SignInShouldFailWithWrongEmail() throws Exception {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String password = signUpRequest.getPassword();

        // First, sign up the user
        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        SignInRequest signInRequest = new SignInRequest("", password);

        // Then, sign in the user with the wrong password
        mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(5)
    void SignInShouldFailWithWrongPassword() throws Exception {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String email = signUpRequest.getEmail();

        // First, sign up the user
        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        SignInRequest signInRequest = new SignInRequest(email, "wrongPassword");

        // Then, sign in the user with the wrong password
        mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Order(6)
    void signInShouldRotateTokens() throws Exception {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;

        mockMvc.perform(post("/api/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signUpRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        // First signing in of the user
        SignInRequest signInRequest = AuthFixtures.signInRequest;

        String res1 = mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Tokens tokens1 = json.readValue(res1, Tokens.class);

        // Second signing in of the user
        String res2 = mockMvc.perform(post("/api/auth/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.writeValueAsString(signInRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Tokens tokens2 = json.readValue(res2, Tokens.class);

        assertThat(tokens1.getAccessToken()).isNotEqualTo(tokens2.getAccessToken());
        assertThat(tokens1.getRefreshToken()).isNotEqualTo(tokens2.getRefreshToken());
    }
}
