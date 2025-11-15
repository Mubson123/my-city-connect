package com.mc.oauth.service;

import com.mc.oauth.fixtures.AuthFixtures;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.repository.TokenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TokenServiceTest {
    @Mock
    private TokenRepository tokenRepository;
    @InjectMocks
    private TokenService tokenService;


    @Test
    void shouldReturnTokensByFoundEmail() {
        Tokens tokens = AuthFixtures.tokens;
        String email = AuthFixtures.user.getEmail();
        when(tokenRepository.findByEmail(email)).thenReturn(Optional.of(tokens));
        tokenService.registryTokens(tokens, email);
        verify(tokenRepository, times(1)).findByEmail(email);
    }

    @Test
    void shouldCreateNewTokensWhenUserNotExist() {
        Tokens tokens = AuthFixtures.tokens;
        String email = AuthFixtures.user.getEmail();
        when(tokenRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(tokenRepository.save(tokens)).thenReturn(tokens);
        Tokens actual = tokenService.registryTokens(tokens, email);
        assertThat(actual).isNotNull();
        verify(tokenRepository, times(1)).findByEmail(email);
        verify(tokenRepository, times(1)).save(tokens);
    }

    @Test
    void shouldThrowExceptionByEmptyEmail() {
        Tokens tokens = AuthFixtures.tokens;
        String email = "";
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> tokenService.registryTokens(tokens, email));
        assertThat(throwable.getMessage()).isEqualTo("Email cannot be null or empty");
        verifyNoInteractions(tokenRepository);
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        Tokens tokens = AuthFixtures.tokens;
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> tokenService.registryTokens(tokens, null));
        assertThat(throwable.getMessage()).isEqualTo("Email cannot be null or empty");
        verifyNoInteractions(tokenRepository);
    }

    @Test
    void shouldThrowExceptionWhenTokensIsNull() {
        String email = AuthFixtures.user.getEmail();
        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> tokenService.registryTokens(null, email));
        assertThat(throwable.getMessage()).isEqualTo("Tokens cannot be null");
        verifyNoInteractions(tokenRepository);
    }
}
