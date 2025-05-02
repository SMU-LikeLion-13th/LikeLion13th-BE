package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResDTO {

    @Builder
    public record CommentListResDTO(
            List<CommentDetailResDTO> comments
    ) {
    }

    @Builder
    public record CommentDetailResDTO(
            Long memberId,
            Long reviewId,
            Long commentId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Integer likeCount
    ) {
    }

    @Builder
    public record CommentCreateResDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }
}
