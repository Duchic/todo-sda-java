package com.example.todosdajava.auth;

import com.example.todosdajava.services.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    public final CustomUserService customUserService;

    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(
            CustomUserService customUserService,
            PasswordEncoder passwordEncoder
    ) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authorizationProvider = new DaoAuthenticationProvider();

        authorizationProvider.setUserDetailsService(customUserService);
        authorizationProvider.setPasswordEncoder(passwordEncoder);

        return authorizationProvider;
    }

    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth", "/api/v1/users")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
