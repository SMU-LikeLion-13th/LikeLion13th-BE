package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class MemberListRequestDTO {
        @Schema(description = "회원 목록")
        private List<MemberRequestDTO.MemberReqDTO> members;
    }

    @Getter
    public static class MemberReqDTO {

        @Schema(description = "회원 설명", example = "어떤 회원인지 설명")
        private String note;
    }

    // 사용자 회원가입
    public record MemberCreateRequestDTO (
            String email,
            String name,
            String password,
            String image
    ){
    }

    @Getter
    public static class PasswordResetDTO {
        @Schema(description = "새로우 비밀번호", example = "newpassword1234")
        @NotBlank(message = " 새 비밀번호는 필수 입력 값입니다.")
        @Size(min = 8, max = 20, message = "비밀번호는 8 ~ 20자 사이여야 합니다.")
        private String password;
    }
}
