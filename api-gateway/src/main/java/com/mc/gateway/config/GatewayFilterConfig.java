package com.mc.gateway.config;

import com.mc.gateway.filter.JwtValidationFilterFactory;
import com.mc.gateway.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterConfig {
    @Bean
    public JwtValidationFilterFactory jwtValidationFilterFactory(JwtService jwtService) {
        return new JwtValidationFilterFactory(jwtService);
    }
}
