package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResDTO {
    @Builder
    public record CommentDetailResDTO(
            Long commentId,
            String description,
            String name,
            Integer likeCount,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CommentListResDTO(
            List<CommentDetailResDTO> commentList
    ) {
    }

    @Builder
    public record CommentCreateResDTO(
            Long commentId
    ) {
    }
}
