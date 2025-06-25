package com.mullen.commander.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/user", "/login/oauth2/**", "/api/v1/user/login", "/swagger-ui/**", "/v3/**").permitAll()
                        .anyRequest().authenticated()
                ).oauth2Login((auth) -> auth.successHandler(new OAuth2SuccessHandler()).failureHandler((request, response, exception) -> {
                            response.sendRedirect("http://localhost:3001/error?message=" + exception.getMessage());
                            exception.printStackTrace();
                        }
                ))
                .build();
    }
}