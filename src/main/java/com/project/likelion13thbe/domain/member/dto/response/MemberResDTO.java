package com.project.likelion13thbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class MemberResDTO {
    public record Test1DTO(
            Long id,
            String content
    ) {
    }
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public class MemberResponseDTO {
        private Long id;
        private String content;

        @Schema(description = "Member의 PK? UserId")
        private Long userId;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MemberCreateResDTO {
        private Long id;
        private LocalDateTime createdAt;
    }
}
