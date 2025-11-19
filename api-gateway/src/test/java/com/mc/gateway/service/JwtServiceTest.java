package com.mc.gateway.service;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class JwtServiceTest {

    @Test
    void shouldReturnTrueWhenTokenIsValid() {
        // Arrange
        JwtService jwtService = Mockito.spy(new JwtService());

        Claims claims = mock(Claims.class);
        when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() + 10_000)); // future

        doReturn(claims).when(jwtService).getClaims("valid-token");

        // Act
        boolean result = jwtService.isValidToken("valid-token");

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenTokenIsExpired() {
        // Arrange
        JwtService jwtService = Mockito.spy(new JwtService());

        Claims claims = mock(Claims.class);
        when(claims.getExpiration()).thenReturn(new Date(System.currentTimeMillis() - 10_000)); // past

        doReturn(claims).when(jwtService).getClaims("expired-token");

        // Act
        boolean result = jwtService.isValidToken("expired-token");

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenExceptionIsThrown() {
        // Arrange
        JwtService jwtService = Mockito.spy(new JwtService());

        doThrow(new RuntimeException("Invalid token"))
                .when(jwtService)
                .getClaims("bad-token");

        // Act
        boolean result = jwtService.isValidToken("bad-token");

        // Assert
        assertFalse(result);
    }
}
