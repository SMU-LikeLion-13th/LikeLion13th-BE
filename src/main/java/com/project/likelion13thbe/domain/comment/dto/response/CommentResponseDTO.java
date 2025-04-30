package com.project.likelion13thbe.domain.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class CommentResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentListResponseDTO {
        @Schema(description = "댓글 목록")
        private List<CommentResDTO> comments;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentCreateResponseDTO {
        @Schema(description = "댓글")
        private List<CommentResDTO> comment;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResDTO {
        @Schema(description = "댓글의 pk", example = "1")
        private Long commentId;

        @Schema(description = "댓글 내용", example = "정말 좋은 제품이에요!")
        private String content;
    }
}
