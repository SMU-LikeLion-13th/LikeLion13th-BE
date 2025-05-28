package com.project.likelion13thbe.global.security.repository;

public interface RefreshTokenRedisRepository {
    void save(String email, String refreshToken, Long expirationMillis);
    String get(String email);
    void delete(String email);
}
