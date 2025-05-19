package com.project.likelion13thbe.global.security.jwt;

import lombok.Builder;

@Builder
public record JwtDTO(
        String accessToken,
        String refreshToken
) {
}