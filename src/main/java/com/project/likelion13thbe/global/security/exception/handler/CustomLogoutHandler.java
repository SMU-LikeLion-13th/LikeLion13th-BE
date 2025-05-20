package com.project.likelion13thbe.global.security.exception.handler;

import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    // 얘는 이름만 핸들러고 예외 핸들러는 아니고 로그아웃 이벤트가 발생했을 때,
    // 내가 할 작업을 정의하는 뭐 그런거라고 하네요..

    private final JwtUtil jwtUtil;
    // redis로 메모리에 저장하는 방식을 쓴다고 알게 되었습니다.
    // Jwt가 stateless하기에 서버는 얘가 뭐 로그인 했는지 안했는지도 몰라서
    // 로그아웃을 한 순간 얘는 만료될 때까지 못 쓰는 토큰이다라고 블랙리스트로 올리는 방식입니다.
    // 로그인할 때, 저장하고, 로그아웃할 때 삭제하는 화이트 리스트 방식은 세션 처럼 동작한다는데, 차차 알아보겠습니다
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = jwtUtil.resolveAccessToken(request);

        jwtUtil.validateToken(token);

        // 로그아웃 블랙리스트 등록
        long expiration = jwtUtil.getExpiration(token);
        log.info("[ Redis 저장 ] key = Logout {}, 남은시간 = {}", token, expiration);
        redisTemplate.opsForValue().set("Logout " + token, "logout", expiration, TimeUnit.MILLISECONDS);
        log.info("[ CustomLogoutHandler ] Logout 블랙리스트 등록 완료");
    }
}
