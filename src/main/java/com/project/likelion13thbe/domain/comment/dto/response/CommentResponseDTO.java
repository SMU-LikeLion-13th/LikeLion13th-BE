package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResponseDTO {

    @Builder
    public record CommentDetailResponseDTO(
            Long commentId,
            String content,
            String nickname,
            Integer likeCount,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
    }

    @Builder
    public record CommentListResponseDTO(
            List<CommentDetailResponseDTO> commentList
    ) {
    }

    @Builder
    public record CommentCreateResponseDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CommentCursorResponseDTO(
            List<CommentDetailResponseDTO> comments,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}
