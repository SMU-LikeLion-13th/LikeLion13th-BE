package com.project.likelion13thbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
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
    public record MemberCreateResponseDTO (
        @Schema(description = "생성된 회원 정보")
        MemberResDTO member
    ){
    }

    // 사용자 회원가입
    @Builder
    public record MemberCreateResDTO (
            Long id,
            LocalDateTime createdAt
    ){
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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberPreviewResDTO {
        // 사용자 정보 조회
        private Long id;
        private String email;
        private Integer age;
    }


    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberOffsetResDTO {
        // 사용자 정보 페이지네이션 Offset
        private List<MemberPreviewResDTO> members;
        private Long totalElements;
        private Integer totalPages;
    }
}
