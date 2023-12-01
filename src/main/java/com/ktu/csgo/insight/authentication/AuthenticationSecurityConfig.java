package com.ktu.csgo.insight.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class AuthenticationSecurityConfig {

    private final InsightAuthenticationFilter insightAuthenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/api/auth/**", "/error").permitAll()
                                .requestMatchers("/api/tournaments", "/api/tournaments/*").hasAnyRole("ADMIN", "MANAGER")
                                .requestMatchers("/api/tournaments/*/matches", "/api/tournaments/*/matches/**").hasAnyRole("ADMIN")
                                .anyRequest().authenticated())
                .addFilterBefore(insightAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new InsightAuthenticationEntryPoint());

        return httpSecurity.build();
    }
}
