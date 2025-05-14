package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class MemberReqDTO {

    public record MemberCreateReqDTO(
            String email,
            String name,
            Long password
    ) {}

    public record PasswordResetDTO(
            @Schema(description = "새로운 비밀번호", example = "newpassword123")
            @NotBlank(message = "새 비밀번호는 필수 입력값입니다.")
            @Size(min = 8, max = 20, message = "비밀번호는 8~20자 사이여야 합니다.")
            Long password,
            Long memberId
    ) {}

    public record MemberDeleteDTO(
            Long memberId
    ) {}


};