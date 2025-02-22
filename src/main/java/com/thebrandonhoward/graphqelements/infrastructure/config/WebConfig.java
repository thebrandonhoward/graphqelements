package com.thebrandonhoward.graphqelements.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/graphql") // Or your WebSocket endpoint path if different
                .allowedOrigins("http://localhost:3000", "http://localhost:8080","ws://localhost:8080","wss://localhost:8080") // Client origins
                .allowCredentials(true) // Essential for WebSockets and auth
                .allowedMethods("GET", "POST", "OPTIONS") // Allowed HTTP methods (OPTIONS is important for preflight requests)
                .allowedHeaders("*"); // Or specify allowed headers (e.g., "Content-Type", "Authorization")
    }
}
