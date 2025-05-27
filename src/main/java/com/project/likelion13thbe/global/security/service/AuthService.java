package com.project.likelion13thbe.global.security.service;


import com.project.likelion13thbe.global.redis.util.RedisUtil;
import com.project.likelion13thbe.global.security.dto.JwtDTO;
import com.project.likelion13thbe.global.security.entity.Token;
import com.project.likelion13thbe.global.security.exception.AuthErrorCode;
import com.project.likelion13thbe.global.security.exception.AuthException;
import com.project.likelion13thbe.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;


    public JwtDTO reissueToken(JwtDTO jwtDto) throws SignatureException {

        log.info("[ Auth Service ] 토큰 재발급을 시작합니다.");
        String accessToken = jwtDto.getAccessToken();
        String refreshToken = jwtDto.getRefreshToken();

        //Access Token 으로부터 사용자 Email 추출
        String email = jwtUtil.getEmail(refreshToken); // **수정부분**
        log.info("[ Auth Service ] Email ---> {}", email);

        //redis에서 저장된 Refresh Token 가져오기
        String redisRefreshToken = redisUtil.getRefreshToken(email);
        if (redisRefreshToken == null) {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }

        //Refresh Token 이 유효한지 검사
        jwtUtil.validateToken(refreshToken);

        log.info("[ Auth Service ] Refresh Token 이 유효합니다.");

        //만약 redis 에서 찾은 Refresh Token 과 파라미터로 온 Refresh Token 이 일치하면 새로운 토큰 발급
        if (redisRefreshToken.equals(refreshToken)) {
            log.info("[ Auth Service ] 토큰을 재발급합니다.");
            return jwtUtil.reissueToken(refreshToken);
        } else {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}