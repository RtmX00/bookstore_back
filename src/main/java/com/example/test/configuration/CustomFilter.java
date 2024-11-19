package com.example.test.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Do something before the request reaches the controller
        System.out.println("Request intercepted by CustomFilter"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   );

        // Continue with the next filter or the target resource (controller, etc.)
        filterChain.doFilter(request, response);

        // Do something after the response leaves the controller
        System.out.println("Response intercepted by CustomFilter");
    }
}
