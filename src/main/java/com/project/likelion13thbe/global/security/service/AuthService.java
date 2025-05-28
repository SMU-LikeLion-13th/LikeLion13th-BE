package com.project.likelion13thbe.global.security.service;


import com.project.likelion13thbe.global.security.JwtUtil;
import com.project.likelion13thbe.global.security.dto.JwtDTO;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenServcie;

    public JwtDTO.JwtResDTO reissueToken(JwtDTO.JwtResDTO jwtDto) {

        log.info("[ Auth Service ] 토큰 재발급을 시작합니다.");
        String refreshToken = jwtDto.refreshToken();

        //Access Token 으로부터 사용자 Email 추출
        String email = jwtUtil.getEmail(refreshToken); // **수정부분**
        log.info("[ Auth Service ] Email ---> {}", email);

        String storedToken = refreshTokenServcie.getRefreshToken(email);
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new AuthException(AuthErrorCode._INVALID_TOKEN);
        }
        //Refresh Token 이 유효한지 검사
        jwtUtil.validateToken(refreshToken);

        log.info("[ Auth Service ] Refresh Token 이 유효합니다.");

        return jwtUtil.reissueToken(refreshToken);
    }
}