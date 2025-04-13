package com.project.likelion13thbe.domain.member.dto.request;

import lombok.Builder;

public class MemberRequestDTO {

    @Builder
    public record loginRequestDTO(
            String email,
            String password) {
    }

    @Builder
    public record ResetPasswordRequestDTO(
            String password
    ) {
    }
}
