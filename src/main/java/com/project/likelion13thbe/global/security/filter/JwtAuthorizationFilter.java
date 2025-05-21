package com.project.likelion13thbe.global.security.filter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.entity.CustomUserDetails;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter { // OncePerRequestFilter: 같은 HTTP 요청에 대해 필터가 딱 한 번만 실행되도록 보장

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("[ JwtAuthorizationFilter ] 인가 필터 작동 ");

        try {
            // Request 에서 access token 추출
            String accessToken = jwtUtil.resolveAccessToken(request);

            // accessToken이 없다면 다음 필터로 넘어감
            if (accessToken == null) {
                log.info("[ JwtAuthorizationFilter ] Access Token이 존재하지 않습니다. 다음 필터로 넘어갑니다.");
                filterChain.doFilter(request, response);
                return;
            }

            // 토큰 인증
            authenticateAccessToken(accessToken);

            log.info("[ JwtAuthorizationFilter ] 종료. 다음 필터로 넘어갑니다.");
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            logger.warn("[ JwtAuthorizationFilter ] accessToken이 만료되었습니다.");
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 401 return
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Access Token이 만료되었습니다.");
        }
    }

    private void authenticateAccessToken(String accessToken) {
        log.info("[ JwtAuthorizationFilter ] 토큰으로 인가 과정을 시작합니다.");

        // AccessToken 유효성 검증
        jwtUtil.validateToken(accessToken);
        log.info("[ JwtAuthorizationFilter ] Access Token 유효성 검증 성공");

        // email 추출
        String email = jwtUtil.getEmail(accessToken);

        // DB에서 member 조회
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // CustomUserDetail 객체 생성
        CustomUserDetails userDetails = new CustomUserDetails(member);

        log.info("[ JwtAuthorizationFilter ] UserDetails 객체 생성 성공");

        // Spring Security 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        // SecurityContextHolder에 현재 인증 객체 저장
        SecurityContextHolder.getContext().setAuthentication(authToken);

        log.info("[ JwtAuthorizationFilter ] 인증 객체 저장 완료");
    }
}
