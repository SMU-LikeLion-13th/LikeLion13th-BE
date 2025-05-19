package com.project.likelion13thbe.global.security.dto;

import com.project.likelion13thbe.domain.member.entity.Role;
import lombok.Builder;

public class JwtDTO{
    @Builder
    public record JwtResDTO(
            String accessToken,
            String refreshToken
    ){}

    @Builder
    public record JwtLoginResDTO(
            Role role,
            String username,
            String accessToken,
            String refreshToken
    ){}
}
