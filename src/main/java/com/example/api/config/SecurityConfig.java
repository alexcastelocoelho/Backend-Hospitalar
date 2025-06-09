package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/paciente").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.GET, "/paciente").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/paciente/*").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/paciente").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.DELETE, "/paciente").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/responsavel").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.GET, "/responsavel").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/responsavel/*").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/responsavel").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.DELETE, "/responsavel").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/medico").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.GET, "/medico").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.GET, "/medico/*").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.PATCH, "/medico").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.DELETE, "/medico").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.POST, "/consulta").hasAnyRole("ADMIN", "ATENDENTE")
                        .requestMatchers(HttpMethod.GET, "/consulta").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO")
                        .requestMatchers(HttpMethod.GET, "/consulta/*").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.PATCH, "/consulta").hasAnyRole("ADMIN", "ATENDENTE", "MEDICO", "PACIENTE")
                        .requestMatchers(HttpMethod.DELETE, "/consulta").hasAnyRole("ADMIN", "ATENDENTE")
                        .anyRequest().authenticated()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
