package com.project.likelion13thbe.global.security.jwt;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.entity.CustomUserDetails;
import com.project.likelion13thbe.global.security.repository.TokenRepository;
import com.project.likelion13thbe.global.security.entity.Token;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long accessExpMs;
    private final Long refreshExpMs;
    private final TokenRepository tokenRepository;
    private final MemberRepository memberRepository;

    public JwtUtil(
            @Value("${spring.jwt.secret}") String secret,
            @Value("${spring.jwt.token.access-expiration-time}") Long access,
            @Value("${spring.jwt.token.refresh-expiration-time}") Long refresh,
            TokenRepository tokenRepo,
            MemberRepository memberRepository) {
        this.secretKey = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessExpMs = access;
        this.refreshExpMs = refresh;
        this.tokenRepository = tokenRepo;
        this.memberRepository = memberRepository;
    }

    // JWT 토큰을 입력받아 토큰의 Subject로부터 사용자 Email을 추출하는 메서드
    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // token 발급 메서드
    public String tokenProvider(CustomUserDetails customUserDetails, Instant expriation) {

        log.info("[ JwtUtil ] 토큰을 새로 생성합니다.");

        // 현재 시간
        Instant issuedAt = Instant.now();

        // 토큰에 부여할 권한
        String authorities = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .header()
                .add("type", "JWT")
                .and()
                .subject(customUserDetails.getUsername())
                .claim("role", authorities)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expriation))
                .signWith(secretKey)
                .compact();
    }

    // 새로운 JWT access 토큰 생성
    public String createJwtAccessToken(CustomUserDetails customUserDetails) {
        Instant expiration = Instant.now().plusMillis(accessExpMs);

        return tokenProvider(customUserDetails, expiration);
    }

    // 새로운 JWT refresh 토큰 생성
    public String createJwtRefreshToken(CustomUserDetails customUserDetails) {
        Instant expiration = Instant.now().plusMillis(refreshExpMs);
        String refreshToken = tokenProvider(customUserDetails, expiration);

        // save Refresh Token to DB
        tokenRepository.save(Token.builder()
                .email(customUserDetails.getUsername())
                .token(refreshToken)
                .build());

        return refreshToken;
    }

    // 제공된 refresh 토큰 기반 JwtDto 쌍을 다시 발급
    public JwtDTO reissueToken(String refreshToken) {
        // 토큰에서 이메일 추출
        String email = getEmail(refreshToken);

        // 기존 refresh token 삭제
        tokenRepository.deleteByEmail(email);

        // DB에서 해당 사용자 조회
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // CustomUserDetails 생성
        CustomUserDetails userDetails = new CustomUserDetails(member);

        log.info("[ JwtUtil ] 새로운 토큰을 재발급합니다.");

        // 재발급
        return new JwtDTO(
                createJwtAccessToken(userDetails),
                createJwtRefreshToken(userDetails)
        );
    }


    // HTTP 요청의 'Authorization' 헤더에서 JWT 액세스 토큰 검색
    public String resolveAccessToken(HttpServletRequest request) {
        log.info("[ JwtUtil ] 헤더에서 토큰을 추출합니다.");

        String tokenFromHeader = request.getHeader("Authorization");

        if (tokenFromHeader == null || !tokenFromHeader.startsWith("Bearer ")) {
            log.warn("[ JwtUtil ] Request Header에 토큰이 존재하지 않습니다.");
            return null;
        }

        log.info("[ JwtUtil ] 헤더에 토큰이 존재합니다.");

        return tokenFromHeader.split(" ")[1]; // Bearer와 분리하여 토큰 추출
    }

    public void validateToken(String token) {
        log.info("[ JwtUtil ] 토큰의 유효성을 검증합니다.");

        try {
            // 구문 분석 시스템의 시계가 JWT를 생성한 시스템의 시계 오차 고려
            // 약 3분 허용
            long seconds = 3 * 60;
            boolean isExpired = Jwts
                    .parser()
                    .clockSkewSeconds(seconds) // 오차 허용
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration()
                    .before(new Date());

            if (isExpired){
                log.info("만료된 JWT 토큰입니다.");
                throw new ExpiredJwtException(null, null, "만료된 JWT 토큰입니다.");
            }


        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // 원하는 Exception throw
            throw new SecurityException("잘못된 토큰입니다.");
        } catch (ExpiredJwtException e) {
            throw new ExpiredJwtException(null, null, "만료된 JWT 토큰입니다.");
        }
    }
}
