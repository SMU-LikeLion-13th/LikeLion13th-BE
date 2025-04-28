package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

public class MemberResDTO {
    @Builder
    public record ResetPasswordReqDTO(
            String currentPassword,
            String newPassword
    ) {
    }
    @Builder
    public record LoginJwtTokenResDTo(
            String tokenType,
            String accessToken,
            String expiresIn,
            String refreshToken,
            String refreshTokenExpiresIn,
            String scope,
            Long userId,
            String username
    ) {
    }
}
