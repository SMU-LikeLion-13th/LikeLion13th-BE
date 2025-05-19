package com.project.likelion13thbe.domain.member.dto.request;

import com.project.likelion13thbe.domain.member.entity.Role;
import com.project.likelion13thbe.domain.member.entity.SocialType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
            @NotBlank(message = "이름은 필수 입력값입니다.")
            String nickname,
            @NotBlank(message = "이메일은 필수 입력값입니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            String password,
            Role role,
            SocialType socialType,
            String profileImage,
            Integer age
    ) {
    }

    public record PasswordResetDTO (
        @Schema(description = "새로운 비밀번호", example = "newpassword123")
        @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
        String password
    ) {
    }

    public record LoginReqDTO (
            String email,
            String password
    ) {
    }
}
