package com.mc.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/gateway")
@RequiredArgsConstructor
public class GatewayInfoController {

    private final RouteLocator routeLocator;

    @GetMapping("/info")
    public Flux<Map<String, String>> getGatewayInfo() {
        return routeLocator.getRoutes()
                .map(route -> {
                    Map<String, String> routeInfo = new HashMap<>();
                    routeInfo.put("id", route.getId());
                    routeInfo.put("uri", route.getUri().toString());
                    routeInfo.put("predicates", route.getPredicate().toString());
                    return routeInfo;
                });
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "gateway-service");
        return status;
    }
}
