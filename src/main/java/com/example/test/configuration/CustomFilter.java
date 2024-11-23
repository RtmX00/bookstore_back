package com.example.test.configuration;

import com.raika.customexception.exceptions.CustomException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
              {
        try {

            System.out.println("Request intercepted by CustomFilter");
            if(request.getRequestURI().equals("/")){
               response.sendRedirect("swagger-ui/index.html#");
                return;
            }
            filterChain.doFilter(request, response);
            System.out.println("Response intercepted by CustomFilter");
        }catch(CustomException.NewException e){
            throw new CustomException.NewException(e.getMessage() , e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
