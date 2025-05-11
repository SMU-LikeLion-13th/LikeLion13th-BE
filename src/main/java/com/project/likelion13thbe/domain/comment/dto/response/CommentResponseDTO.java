package com.project.likelion13thbe.domain.comment.dto.response;

import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
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
    public static class CommentOffsetResponseDTO {
        private List<CommentPreviewResDTO> comments;
        private Long totalElements;
        private Integer totalPages;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentCreateResponseDTO {
        @Schema(description = "댓글의 pk", example = "1")
        private Long commentId;

        @Schema(description = "작성자", example = "홍길동")
        private String nickname;

        @Schema(description = "댓글 내용", example = "정말 좋은 제품이에요!")
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResDTO {
        @Schema(description = "댓글의 pk", example = "1")
        private Long commentId;

        @Schema(description = "작성자", example = "홍길동")
        private String nickname;

        @Schema(description = "댓글 내용", example = "정말 좋은 제품이에요!")
        private String content;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentPreviewResDTO {
        @Schema(description = "댓글의 pk", example = "1")
        private Long commentId;

        @Schema(description = "작성자", example = "홍길동")
        private String nickname;

        @Schema(description = "댓글 내용", example = "정말 좋은 제품이에요!")
        private String content;
    }
}
