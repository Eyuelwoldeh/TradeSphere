package com.tradeplatform.tradeanalytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF (useful for testing POST requests)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all endpoints with NO authentication
                )
                .httpBasic().disable(); // Disable Basic Auth

        return http.build();
    }
}