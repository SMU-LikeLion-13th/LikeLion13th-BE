package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

public class MemberResDTO {
    @Builder
    public record ResetPasswordReqDTO(
            String currentPassword,
            String newPassword
    ) {
    }
}
