package com.mc.oauth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration-time}")
    private Long jwtExpirationMs;
    @Value("${jwt.refresh-expiration-time}")
    private Long jwtRefreshExpirationMs;

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isRefreshTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis() - jwtRefreshExpirationMs));
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateAccessToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "access");
        return generateToken(authentication, jwtExpirationMs, claims);
    }

    public String generateRefreshToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("tokenType", "refresh");
        return generateToken(authentication, jwtRefreshExpirationMs, claims);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private  <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private String generateToken(Authentication authentication, Long expirationMs, Map<String, Object> claims) {
        Date now = new Date();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("role", userDetails.getAuthorities())
                .claims(claims)
                .issuedAt(now)
                .header()
                .add("alg", "HS256")
                .add("iss", "Spring JWT")
                .add("aud", "User")
                .add("jti", UUID.randomUUID().toString())
                .and()
                .expiration(new Date(now.getTime() + expirationMs))
                .signWith(getSecretKey())
                .compact();
    }
}
