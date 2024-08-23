package com.example.OAuthAndOpenIDConnectLab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecConfig {
    @Bean
    public OAuth2LoginConfigurer<HttpSecurity> securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .and()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/user").authenticated()
                                .requestMatchers("/", "/error", "/webjars/**").permitAll()
                                .requestMatchers("/auth/logout").permitAll()

                ).logout(
                        logout -> logout.logoutSuccessUrl("/").permitAll()
                )
                .sessionManagement(
                        session -> session
                                .sessionFixation()
                                .migrateSession()
                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                                .maximumSessions(1)
                )
                .oauth2Login();
    }
}
