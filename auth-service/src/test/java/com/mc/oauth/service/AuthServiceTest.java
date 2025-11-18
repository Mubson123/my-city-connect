package com.mc.oauth.service;

import com.mc.oauth.dto.SignInRequest;
import com.mc.oauth.dto.SignUpRequest;
import com.mc.oauth.fixtures.AuthFixtures;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.models.User;
import com.mc.oauth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private JwtService jwtService;
    @Mock
    private TokenService tokenService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthService authService;


    @Test
    void signUpShouldSaveUserWhenEmailDoesNotExist() {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String password = signUpRequest.getPassword();
        String email = signUpRequest.getEmail();
        when(userRepository.existsByEmail(email)).thenReturn(Boolean.FALSE);
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");

        authService.signUp(signUpRequest);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        User savedUser = captor.getValue();

        assertEquals(email, savedUser.getEmail());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertNotNull(savedUser.getCreatedAt());
        assertNotNull(savedUser.getUpdatedAt());
    }

    @Test
    void signUpShouldThrowExceptionWhenEmailAlreadyExists() {
        SignUpRequest signUpRequest = AuthFixtures.signUpRequest;
        String email = signUpRequest.getEmail();
        when(userRepository.existsByEmail(email)).thenReturn(Boolean.TRUE);

        Throwable throwable = assertThrows(IllegalStateException.class,
                () -> authService.signUp(signUpRequest));
        assertEquals("Email is already in use!", throwable.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    void signInShouldAuthenticateAndReturnTokens() {
        SignInRequest signInRequest = AuthFixtures.signInRequest;
        String email = signInRequest.getEmail();
        Authentication authResult = mock(Authentication.class);
        Tokens generatedTokens = new Tokens(email, "accessToken", "refreshToken");
        Tokens finalTokens = new Tokens(email, "storedAccessToken", "storedRefreshToken");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authResult);
        when(jwtService.createTokens(authResult)).thenReturn(generatedTokens);
        when(tokenService.registryTokens(generatedTokens, email)).thenReturn(finalTokens);

        Tokens actual = authService.signIn(signInRequest);

        assertEquals(finalTokens, actual);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).createTokens(authResult);
        verify(tokenService).registryTokens(generatedTokens, email);
    }
}
