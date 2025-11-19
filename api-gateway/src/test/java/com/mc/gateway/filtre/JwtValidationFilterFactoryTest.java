package com.mc.gateway.filtre;

import com.mc.gateway.filter.JwtValidationFilterFactory;
import com.mc.gateway.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtValidationFilterFactoryTest {

    @Mock
    private JwtService jwtService;
    @InjectMocks
    private JwtValidationFilterFactory filterFactory;


    private GatewayFilterChain mockChain() {
        return mock(GatewayFilterChain.class);
    }

    // ----------------------------------------------------------
    // NO AUTH HEADER
    // ----------------------------------------------------------
    @Test
    void shouldReturnUnauthorizedWhenHeaderMissing() {
        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/test")
        );

        GatewayFilter filter = filterFactory.apply(new Object());

        filter.filter(exchange, mockChain()).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
    }

    // ----------------------------------------------------------
    // BAD AUTH HEADER FORMAT
    // ----------------------------------------------------------
    @Test
    void shouldReturnUnauthorizedWhenHeaderNotStartingWithBearer() {
        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/test")
                        .header(HttpHeaders.AUTHORIZATION, "Token 123")
        );

        GatewayFilter filter = filterFactory.apply(new Object());

        filter.filter(exchange, mockChain()).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
    }

    // ----------------------------------------------------------
    // INVALID TOKEN
    // ----------------------------------------------------------
    @Test
    void shouldReturnUnauthorizedWhenTokenInvalid() {
        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/test")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer fake.jwt.token")
        );

        when(jwtService.isValidToken("Bearer fake.jwt.token")).thenReturn(false);

        GatewayFilter filter = filterFactory.apply(new Object());

        filter.filter(exchange, mockChain()).block();

        assertEquals(HttpStatus.UNAUTHORIZED, exchange.getResponse().getStatusCode());
    }

    // ----------------------------------------------------------
    // VALID TOKEN
    // ----------------------------------------------------------
    @Test
    void shouldCallNextFilterWhenTokenIsValid() {
        GatewayFilterChain chain = mock(GatewayFilterChain.class);

        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/test")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer good.jwt.token")
        );

        when(jwtService.isValidToken("Bearer good.jwt.token")).thenReturn(true);
        when(chain.filter(any(ServerWebExchange.class))).thenReturn(Mono.empty());

        GatewayFilter filter = filterFactory.apply(new Object());
        filter.filter(exchange, chain).block();

        verify(chain, times(1)).filter(exchange);
        assertNull(exchange.getResponse().getStatusCode()); // pas de statut forcé
    }
}