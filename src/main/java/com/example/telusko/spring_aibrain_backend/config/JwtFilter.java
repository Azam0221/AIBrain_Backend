package com.example.telusko.spring_aibrain_backend.config;


import com.example.telusko.spring_aibrain_backend.model.AuthResponse;
import com.example.telusko.spring_aibrain_backend.service.JwtService;
import com.example.telusko.spring_aibrain_backend.service.MyUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext context;
    @Autowired
    private View error;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getServletPath();
        if(path.contains("userLogin") || path.contains("userRegister")){
            filterChain.doFilter(request,response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token="";
        String email = "";

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7);
            email    = jwtService.extractUserName(token);
        }


        if(email!=null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(email);


            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );

                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);


    }

    private void sendError(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        AuthResponse error = new AuthResponse(
                status.value(),
                message,
                null,
                null,
                Map.of("token", message),
                LocalDateTime.now()
        );

        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), error);

    }
}
