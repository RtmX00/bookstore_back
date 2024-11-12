package com.example.test.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig { // extends WebSecurityConfigurerAdapter {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/**").permitAll()
                        /*.requestMatchers("/user/**").permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/Admin/**").permitAll()
                        .requestMatchers("/Client/**").permitAll()
                        .requestMatchers("/product/**").permitAll()*/
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
