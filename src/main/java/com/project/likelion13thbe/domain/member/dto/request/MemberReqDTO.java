package com.project.likelion13thbe.domain.member.dto.request;

import com.project.likelion13thbe.domain.member.entity.SocialType;

public class MemberReqDTO {

    public record ResetPasswordReqDTO(
            String currentPassword,
            String newPassword
    ) {
    }

    public record SignUpResDTO(
            String nickname,
            String email,
            String password,
            SocialType socialType
    ) {
    }

    public record LoginResDTO(
            String email,
            String password
    ) {
    }

    public record KakaoLoginResDTO(
            String authorizationCode
    ) {
    }

    public record MemberCreateReqDTO(
            String nickname,
            String email,
            String password,
            SocialType socialType,
            String profileImage,
            Integer age
    ) {
    }
}
