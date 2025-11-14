package com.mc.oauth.service;

import com.mc.oauth.dto.SignInRequest;
import com.mc.oauth.dto.SignUpRequest;
import com.mc.oauth.models.Role;
import com.mc.oauth.models.Tokens;
import com.mc.oauth.models.User;
import com.mc.oauth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new IllegalStateException("Email is already in use!");
        }
        User user = User.builder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
    }

    public Tokens signIn(SignInRequest signInRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(
                signInRequest.getEmail(),
                signInRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Tokens tokens = jwtService.createTokens(authentication);
        return tokenService.registryTokens(tokens, signInRequest.getEmail());
    }
}
