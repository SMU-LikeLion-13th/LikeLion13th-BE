package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record LoginJwtTokenResDTO(
            String tokenType,
            String accessToken,
            String expiresIn,
            String refreshToken,
            String refreshTokenExpiresIn,
            String scope
    ) {
    }

    @Builder
    public record MemberCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record MemberPreviewResDTO(
            Long id,
            String email,
            Integer age
    ) {
    }

    @Builder
    public record MemberOffsetResDTO(
            List<MemberPreviewResDTO> members,
            Long totalElements,
            Integer totalPages
    ) {
    }

    ) {
    }
}
