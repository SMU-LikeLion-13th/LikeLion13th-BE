package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class CommitResDTO {
    @Builder
    public record CommentListResDTO(
            List<CommentResDTO> comments
    ) {
    }

    @Builder
    public record CommentResDTO(
            Long commentId,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String username,
            Integer likeCount
    ) {
    }
}
