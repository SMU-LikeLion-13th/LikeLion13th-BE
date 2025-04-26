package com.project.likelion13thbe.domain.member.dto.request;

import com.project.likelion13thbe.domain.member.entity.SocialType;
import lombok.Builder;

public class MemberRequestDTO {

    @Builder
    public record LoginRequestDTO(
            String email,
            String password) {
    }

    @Builder
    public record ResetPasswordRequestDTO(
            String password
    ) {
    }

    @Builder
    public record SignUpRequestDTO(
            String nickname,
            String email,
            String password,
            SocialType socialType
    ) {
    }

    public record kakaoLoginRequestDTO(
            String authorizationCode
    ) {
    }
}