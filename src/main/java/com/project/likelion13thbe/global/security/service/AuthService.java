package com.project.likelion13thbe.global.security.service;

import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.exception.AuthException;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, String> redisTemplate;

    public JwtDTO reissueToken(String refreshToken, HttpServletResponse response) {
        log.info("[ Auth Service ] 토큰 재발급을 시작합니다");

        // refresh token 유효성 검사
        jwtUtil.validateToken(refreshToken);

        log.info("[ Auth Service ] Refresh Token이 유효합니다.");

        // Refresh Token으로부터 사용자 email 추출
        String email = jwtUtil.getEmail(refreshToken);
        log.info("[ Auth Service ] Email ---> {}", email);

        // DB에 저장된 refresh token 가져오기
        String savedRefreshToken = redisTemplate.opsForValue().get("refreshToken:" + email);

        // DB에서 찾은 refresh token과 요청으로 온 refresh token이 일치하는지 검사
        if (savedRefreshToken == null || !savedRefreshToken.equals(refreshToken)) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }

        // 새 refresh 토큰 재발급
        JwtDTO jwtDTO = jwtUtil.reissueToken(refreshToken);

        // 새 refresh Token을 쿠키에 담아 응답
        Cookie refreshCookie = createCookie("refreshToken", jwtDTO.refreshToken());
        response.addCookie(refreshCookie);

        // 새 access 토큰만 body로 변환
        return JwtDTO.builder()
                .accessToken(jwtDTO.accessToken())
                .build();
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);

        return cookie;
    }
}
