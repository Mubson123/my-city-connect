package com.mc.oauth.service;

import com.mc.oauth.models.Tokens;
import com.mc.oauth.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    public Tokens registryTokens(Tokens tokens, String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        return tokenRepository.findByEmail(email).map(
                token -> {
                    token.setAccessToken(tokens.getAccessToken());
                    token.setRefreshToken(tokens.getRefreshToken());
                    return token;
                }
        ).orElse(tokenRepository.save(tokens));
    }
}
