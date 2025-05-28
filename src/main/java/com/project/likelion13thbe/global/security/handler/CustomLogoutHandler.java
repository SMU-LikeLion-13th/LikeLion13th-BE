package com.project.likelion13thbe.global.security.handler;

import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("[ LogoutHandler ] 로그아웃 요청");


        // access token 추출
        String accessToken = jwtUtil.resolveAccessToken(request);

        // access token 블랙리스팅
        if (accessToken != null) {
            try {
                jwtUtil.validateToken(accessToken); // 유효성 확인
                jwtUtil.blacklistToken(accessToken); // 블랙리스트 등록
                log.info("[ LogoutHandler ] Access Token 블랙리스트 등록 완료");
            } catch (Exception e) {
                log.warn("[ LogoutHandler ] Access Token 유효성 검증 실패: {}", e.getMessage());
            }
        }

        // refresh token 추출
        String refreshToken = getRefreshTokenFromCookie(request);

        // refresh token 삭제
        if (refreshToken != null) {
            jwtUtil.validateToken(refreshToken);
            String email = jwtUtil.getEmail(refreshToken);
            redisTemplate.delete("refreshToken:" + email);
            log.info("[ LogoutHandler ] Redis에서 Refresh Token 삭제 완료");
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
