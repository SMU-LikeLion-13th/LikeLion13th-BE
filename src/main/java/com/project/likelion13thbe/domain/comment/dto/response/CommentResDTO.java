package com.project.likelion13thbe.domain.comment.dto.response;

public class CommentResDTO {
    public record CommentResponseDTO(
            Long commentId,
            String comment,
            Long likes
    ){}
}