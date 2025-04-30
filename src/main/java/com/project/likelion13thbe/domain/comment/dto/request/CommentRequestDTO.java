package com.project.likelion13thbe.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CommentRequestDTO {

    @Getter
    public static class CommentListRequestDTO {
        @Schema(description = "댓글 목록")
        private List<CommentRequestDTO.CommentReqDTO> comments;
    }

    @Builder
    public record CommentCreateRequestDTO(
            String content,
            String nickname
    ) {}

    @Getter
    public static class CommentReqDTO {
        @Schema(description = "댓글 내용", example = "입력한 댓글 내용")
        private String content;
    }
}
