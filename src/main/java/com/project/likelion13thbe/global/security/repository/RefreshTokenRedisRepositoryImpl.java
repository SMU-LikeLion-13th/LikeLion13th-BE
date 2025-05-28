package com.project.likelion13thbe.global.security.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRedisRepositoryImpl implements RefreshTokenRedisRepository {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void save(String email, String refreshToken, Long expirationMillis) {
        redisTemplate.opsForValue().set(email, refreshToken, Duration.ofMillis(expirationMillis));
    }

    @Override
    public String get(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    @Override
    public void delete(String email) {
        redisTemplate.delete(email);
    }
}
