package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

public class MemberResponseDTO {
    @Builder
    public record JwtTokenResponse(
            String accessToken,
            String refreshToken,
            Long expiresIn
    ) {
    }
}
