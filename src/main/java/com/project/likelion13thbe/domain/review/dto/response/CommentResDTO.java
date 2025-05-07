package com.project.likelion13thbe.domain.review.dto.response;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResDTO {
    public record CommentResponseDTO(
            Long id,
            String content
    ) {
    }

    public record CommentCreateResDTO(
            Long commentId,
            LocalDateTime date
    ) {}

}
