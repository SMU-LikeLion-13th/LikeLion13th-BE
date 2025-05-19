package com.project.likelion13thbe.global.security.service;

import com.project.likelion13thbe.global.security.entity.Token;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.exception.AuthException;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import com.project.likelion13thbe.global.security.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    public JwtDTO reissueToken(JwtDTO jwtDTO) {
        log.info("[ Auth Service ] 토큰 재발급을 시작합니다");

        String accessToken = jwtDTO.accessToken();
        String refreshToken = jwtDTO.refreshToken();

        // Refresh Token으로부터 사용자 email 추출
        String email = jwtUtil.getEmail(refreshToken);
        log.info("[ Auth Service ] Email ---> {}", email);

        // DB에 저장된 refresh token 가져오기
        Token refreshTokenByDB = tokenRepository.findByEmail(email).orElseThrow(
                () -> new AuthException(AuthErrorCode.INVALID_TOKEN)
        );

        // Refresh Token 유효성 검사
        jwtUtil.validateToken(refreshToken);

        log.info("[ Auth Service ] Refresh Token이 유효합니다.");

        // DB에서 찾은 refresh token과 요청으로 온 refresh token이 일치하면 새로운 토큰 발급
        if (refreshTokenByDB.getToken().equals(refreshToken)) {
            log.info("[ Auth Service ] 토큰을 재발급합니다.");
            return jwtUtil.reissueToken(refreshToken);
        } else {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}
