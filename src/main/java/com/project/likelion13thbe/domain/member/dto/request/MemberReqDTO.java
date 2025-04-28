package com.project.likelion13thbe.domain.member.dto.request;

import com.project.likelion13thbe.domain.member.entity.SocialType;
import lombok.Builder;

public class MemberReqDTO {

    @Builder
    public record ResetPasswordReqDTO(
            String currentPassword,
            String newPassword
    ) {
    }

    @Builder
    public record SignUpResDTO(
            String nickname,
            String email,
            String password,
            SocialType socialType
    ) {
    }

    @Builder
    public record LoginResDTO(
            String email,
            String password
    ) {
    }

}
