package com.project.likelion13thbe.domain.member.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberReqDTO {

    @Builder
    public record MemberCreateReqDTO(
            @NotBlank(message = "이름은 필수 입력 항목입니다.")
            @Size(min = 2, max = 50, message = "이름은 2자 이상, 50자 이하로 입력해주세요.")
            String name,

            @NotBlank(message = "이메일은 필수 입력 항목입니다.")
            @Email(message = "올바른 이메일 형식을 입력해주세요.")
            String email,

            @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
            @Size(min = 8, message = "비밀번호는 최소 8자 이상 입력해야 합니다.")
            String password

    ){
    }
    //비밀번호 업데이트 Res
    public record PasswordResetDTO (
            @Schema(description = "새로운 비밀번호", example = "newpassword123")
            @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            String password
    ){
    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginReqDTO {
        private String email;
        private String password;
    }


}
