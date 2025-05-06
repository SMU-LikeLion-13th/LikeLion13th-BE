package com.project.likelion13thbe.domain.comment.dto.request;


public class CommentReqDTO {
    public record CommentCreateRequestDTO(
            Long memberId,
            Long reviewId,
            String content
    ) {
    }

}
