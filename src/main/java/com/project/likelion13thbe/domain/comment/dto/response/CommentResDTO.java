package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class CommentResDTO {
    @Builder
    public record CommentListResDTO(
            List<CommentPreviewResDTO> comments
    ) {
    }

    @Builder
    public record CommentPreviewResDTO(
            Long id,
            String username,
            String content,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record CommentCreateResDTO(
            Long id,
            LocalDateTime createdAt
    ){
    }
}
