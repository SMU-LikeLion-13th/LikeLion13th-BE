package com.project.likelion13thbe.global.Security;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.global.Security.CustomUserDetail.CustomUserDetails;
import com.project.likelion13thbe.global.Security.DTO.JwtDto;
import com.project.likelion13thbe.global.Security.Entity.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    public JwtDto createJwt(Member member) {
        CustomUserDetails customUserDetails = new CustomUserDetails(member.getEmail(), member.getPassword(), member.getRole().toString());

        String accessToken = jwtUtil.createJwtAccessToken(customUserDetails);
        String refreshToken = jwtUtil.createJwtRefreshToken(customUserDetails);

        // RefreshToken 저장 (DB에 갱신)
        tokenRepository.save(new Token(member.getEmail(), refreshToken));

        return new JwtDto(accessToken, refreshToken);
    }

    public JwtDto reissueToken(JwtDto jwtDto) throws SignatureException {

        log.info("[ Auth Service ] 토큰 재발급을 시작합니다.");
        String accessToken = jwtDto.getAccessToken();
        String refreshToken = jwtDto.getRefreshToken();

        //Access Token 으로부터 사용자 Email 추출
        String email = jwtUtil.getEmail(refreshToken); // **수정부분**
        log.info("[ Auth Service ] Email ---> {}", email);

        //Access Token 에서의 Email 로 부터 DB 에 저장된 Refresh Token 가져오기
        Token refreshTokenByDB = tokenRepository.findByEmail(email).orElseThrow(
                () -> new AuthException(AuthErrorCode.INVALID_TOKEN)
        );

        //Refresh Token 이 유효한지 검사
        jwtUtil.validateToken(refreshToken);

        log.info("[ Auth Service ] Refresh Token 이 유효합니다.");

        //만약 DB 에서 찾은 Refresh Token 과 파라미터로 온 Refresh Token 이 일치하면 새로운 토큰 발급
        if (refreshTokenByDB.getToken().equals(refreshToken)) {
            log.info("[ Auth Service ] 토큰을 재발급합니다.");
            return jwtUtil.reissueToken(refreshToken);
        } else {
            throw new AuthException(AuthErrorCode.INVALID_TOKEN);
        }
    }
}