package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

public class MemberReqDTO {
    @Builder
    public record MemberCreateReqDTO (
            @NotBlank(message = "이름은 필수 입력값입니다.")
            String name,

            @NotBlank(message = "이메일은 필수 입력값입니다.")
            @Email(message = "이메일 양식이 맞지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            String password
    ){
    }

    @Builder
    public record ResetPasswordReqDTO(
            @NotBlank(message = "현재 비밀번호는 필수 입력값입니다.")
            @Size(min=8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            String currentPassword,
            @Schema(description = "새로운 비밀번호", example = "newpassword123")
            @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
            @Size(min=8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            String password
    ){
    }
    @Builder
    public record LoginReqDTO(
            @NotBlank(message = "이메일은 필수 입력값입니다.")
            @Email(message = "이메일 양식이 맞지 않습니다.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력값입니다.")
            @Size(min=8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            String password
    ){
    }

    @Builder
    public record KakaoLoginRequestDTO (
            String accessToken
    ){}
}
