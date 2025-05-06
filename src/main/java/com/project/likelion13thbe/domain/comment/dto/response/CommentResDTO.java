package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class CommentResDTO {

    public record CommentResponseDTO(){

    }
    @Builder
    public record CommentCreateResponseDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }


}