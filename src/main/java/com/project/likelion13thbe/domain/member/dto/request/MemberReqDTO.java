package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


public class MemberReqDTO {
    @Builder
    public record LoginRequest(String email, String password) {}

    @Builder
    public record ResetPasswordRequest(String password) {}

    @Builder
    public record  SignUpRequest(  //MemberCreateReqDTO
             String email,
             Integer age,
             String name,
             String password,
             String image
            ) {}
    @Builder
    public record KakaoLoginRequest(
            String kakaoEmail, String kakaoPassword) {}
    @Getter
    public static class PasswordResetDTO{
        @Schema(description="새로운 비밀번호", example = "newpassword123")
        @NotBlank(message="새 비밀번호는 필수 입력값입니다.")
        @Size(min=8,max=20,message="비밀번호는 8~20자 사이여야 합니다. ")
        private String password;
    }
}
