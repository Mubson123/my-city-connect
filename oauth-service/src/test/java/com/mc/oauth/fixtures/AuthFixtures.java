package com.mc.oauth.fixtures;

import com.mc.oauth.dto.SignInRequest;
import com.mc.oauth.dto.SignUpRequest;
import com.mc.oauth.models.Role;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.models.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class AuthFixtures {
    private AuthFixtures() {
        throw new UnsupportedOperationException("Cannot instantiate AuthFixtures");
    }

    public static User user = User.builder()
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .firstname("Max")
            .lastname("Mustermann")
            .email("max.mustermann@domain.com")
            .password("password")
            .role(Role.USER)
            .build();

    public static SignUpRequest signUpRequest = SignUpRequest.builder()
            .firstname("Max")
            .lastname("Mustermann")
            .email("max.mustermann@domain.com")
            .password("password")
            .build();

    public static SignInRequest signInRequest = SignInRequest.builder()
            .email("max.mustermann@domain.com")
            .password("password")
            .build();

    public static Tokens tokens = Tokens.builder()
            .id(UUID.randomUUID())
            .isValidToken(Boolean.TRUE)
            .accessToken("access_token")
            .refreshToken("refresh_token")
            .build();
}
