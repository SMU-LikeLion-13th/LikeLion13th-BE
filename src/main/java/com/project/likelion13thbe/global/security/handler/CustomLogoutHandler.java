package com.project.likelion13thbe.global.security.handler;

import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import com.project.likelion13thbe.global.security.repository.TokenRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("[ LogoutHandler ] 로그아웃 요청");

        String refreshToken = getRefreshTokenFromCookie(request);

        if (refreshToken != null) {
            jwtUtil.validateToken(refreshToken);
            String email = jwtUtil.getEmail(refreshToken);
            tokenRepository.deleteByEmail(email);
        }

        // cookie에서 refresh token 값 0으로 설정
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        log.info("[ LogoutHandler ] 쿠키 삭제 완료");

    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) return null;

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
