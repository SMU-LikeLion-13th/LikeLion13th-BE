package com.project.likelion13thbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class MemberListRequestDTO {
        @Schema(description = "회원 목록")
        private List<MemberRequestDTO.MemberReqDTO> members;
    }

    @Builder
    public record MemberCreateRequestDTO(
            String name,
            String email,
            String password
    ) {}

    @Getter
    public static class MemberReqDTO {
        @Schema(description = "회원 설명", example = "어떤 회원인지 설명")
        private String note;
    }
}
