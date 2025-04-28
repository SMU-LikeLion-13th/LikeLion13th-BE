package com.project.likelion13thbe.domain.member.dto.request;

import lombok.Builder;

public class MemberReqDTO {
    @Builder
    public record SignupReqDTO(
            String username,
            String email,
            String password
    ){
    }

    @Builder
    public record ResetPasswordReqDTO(
            String newPassword
    ){
    }
    @Builder
    public record LoginReqDTO(
            String email,
            String password
    ){
    }

    @Builder
    public record KakaoLoginRequestDTO (
            String accessToken
    ){}
}
