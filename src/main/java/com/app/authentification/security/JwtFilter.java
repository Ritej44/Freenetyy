package com.app.authentification.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.extras.springsecurity6.auth.Authorization;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final jwtService jwtService;
    @Override
    protected void doFilterInternal(
           @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
       ) throws ServletException, IOException {
         if(request.getServletPath().contains("/api/v1/auth")){
             filterChain.doFilter(request,response);
             return;
         }
         final String authHeader = request.getHeader(AUTHORIZATION);
         final String jwt;
         final String userEmail;
         if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
         }
         jwt=authHeader.substring(7);
         userEmail =jwtService.extractUsername(jwt);
    }
}
