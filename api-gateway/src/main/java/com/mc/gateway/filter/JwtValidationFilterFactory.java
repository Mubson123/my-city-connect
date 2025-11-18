package com.mc.gateway.filter;

import com.mc.gateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class JwtValidationFilterFactory extends AbstractGatewayFilterFactory<Object> {
    private final JwtService jwtService;

    @Override
    public GatewayFilter apply(Object config) {
        return ((exchange, chain) -> {

            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                log.warn("Unauthorized access attempt without JWT token");
                return unauthorized(exchange);
            }

            if (!jwtService.isValidToken(authHeader)) {
                log.warn("Invalid JWT token");
                return unauthorized(exchange);
            }
            log.info("JWT token is valid");
            return chain.filter(exchange);
        });
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
