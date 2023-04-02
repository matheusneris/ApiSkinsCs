package com.counterstrike.inventario.system;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Profile("security")
@Configuration
@EnableMethodSecurity(securedEnabled = false, jsr250Enabled = false)
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private static final String[] AUTH_ALLOWLIST = {
            // -- Swagger UI v3
            "/v3/api-docs/**",
            "v3/api-docs/**",
            "/swagger-ui/**",
            "swagger-ui/**",
            "/swagger-ui.html",
            "swagger-ui.html",
            // Actuators
            "/actuator/**",
            "/health/**",
            // Login
            "/rest/usuario/**",
            "/rest/usuario/login",
            "/usuario/**",
            "/skins/**"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(PathRequest.toH2Console()).permitAll()
                                .requestMatchers(AUTH_ALLOWLIST).permitAll()
                                .requestMatchers(HttpMethod.POST, "/rest/usuario/criar").permitAll()
                                .requestMatchers(HttpMethod.GET, "/inventario/salvar").permitAll()
                                .anyRequest().authenticated()
                )
                .headers().frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider);


        return http.build();
    }
}
