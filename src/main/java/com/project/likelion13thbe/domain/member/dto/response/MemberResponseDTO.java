package com.project.likelion13thbe.domain.member.dto.response;

public class MemberResponseDTO {
    public record JwtTokenResponse(
            String accessToken,
            String refreshToken,
            Long expiresIn
    ) {
    }
}
