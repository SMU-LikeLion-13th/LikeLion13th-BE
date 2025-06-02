package com.project.likelion13thbe.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.customUserDetails.CustomUserDetails;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.exception.AuthException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForcePasswordChangeFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            // 그냥 로그인이 안돼있다면 통과
            if (auth == null || !auth.isAuthenticated() || (auth.getPrincipal().equals("anonymousUser"))) {
                                                        // !(auth.getPrincipal() instanceof CustomUserDetails customUserDetails) 이렇게도 가능
                filterChain.doFilter(request, response);
                return;
            }

            CustomUserDetails CustomUserDetails = (CustomUserDetails) auth.getPrincipal();

            // 유저의 상태가 MUST CHANGE PASSWORD 인가?
            boolean mustChange = CustomUserDetails.isPasswordChangeRequired();
            // 현재 uri 정보 저장
            String uri = request.getRequestURI();
            // 임시 비밀번호로 로그인 했을 때, 사용가능한 uri
            boolean isAllowed = uri.startsWith("/password-reset") || uri.equals("/logout");

            // 비밀번호를 바꿔야 하는 상황이고, 바로 안바꾸고 다른 곳으로 간다?
            if (mustChange && !isAllowed) {
                log.warn("[ ForcePasswordChangeFilter ] 임시 비밀번호로 로그인 한 경우, 무조건 비밀번호를 변경해야 합니다.");
                throw new AuthException(AuthErrorCode.NOT_CHANGE_PASSWORD);
            }

            filterChain.doFilter(request, response);

        } catch (AuthException e) {
            CustomResponse<String> responseBody = CustomResponse.onFailure(String.valueOf(e.getCode().getCode()), e.getCode().getMessage());

            ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(responseBody));
        }

    }
}
