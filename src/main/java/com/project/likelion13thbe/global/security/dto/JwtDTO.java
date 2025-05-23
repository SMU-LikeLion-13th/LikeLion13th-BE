package com.project.likelion13thbe.global.security.dto;

import lombok.Builder;

public class JwtDTO {

    private String accessToken;
    private String refreshToken;

    @Builder
    public record JwtResDTO(String accessToken, String refreshToken) {
    }
}