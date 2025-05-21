package com.project.likelion13thbe.domain.member.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {
    @Builder
    public record MemberCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }

    @Builder
    public record MemberPreviewResDTO(
            Long id,
            String name,
            String email
    ){
    }

    @Builder
    public record ResetPasswordResDTO(
            String currentPassword,
            String newPassword
    ) {
    }
}
