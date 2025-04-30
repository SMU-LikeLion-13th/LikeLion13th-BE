package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class MemberResDTO {
    @Builder
    public record JwtTokenResponse(
            String accessToken,
            String refreshToken ){}
}
