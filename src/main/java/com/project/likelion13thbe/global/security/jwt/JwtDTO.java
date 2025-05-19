package com.project.likelion13thbe.global.security.jwt;

public record JwtDTO(
        String jwtAccessToken,
        String jwtRefreshToken
) {
}
