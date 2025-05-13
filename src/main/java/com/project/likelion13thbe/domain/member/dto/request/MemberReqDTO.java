package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class MemberReqDTO {

    @Getter
    public static class MemberCreateReqDTO {
        private String email;
        private String password;
        private String nickname;
    }
    @Getter
    public static class PasswordResetDTO {
        @Schema(description = "새로운 비밀번호", example = "newpassword123")
        @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
        private String password;
    }
}
