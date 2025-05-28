package com.project.likelion13thbe.global.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(401);
        CustomResponse<Object> errorResponse = CustomResponse.onFailure(
                AuthErrorCode.UNAUTHORIZED.getCode(),
                AuthErrorCode.UNAUTHORIZED.getMessage(),
                null
        );
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), errorResponse);
    }

}
