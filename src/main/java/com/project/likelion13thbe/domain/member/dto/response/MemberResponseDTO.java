package com.project.likelion13thbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MemberResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberListResponseDTO {
        @Schema(description = "회원 목록")
        private List<MemberResDTO> members;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberCreateResponseDTO {
        @Schema(description = "생성된 회원 정보")
        private MemberResDTO member;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberResDTO {
        @Schema(description = "회원 ID", example = "1")
        private Long memberId;

        @Schema(description = "회원 이름", example = "홍길동")
        private String name;

        @Schema(description = "회원 이메일", example = "hong@example.com")
        private String email;
    }
}
