package com.project.likelion13thbe.domain.comment.dto.request;

public class CommentReqDTO {

    public record CommentCreateReqDTO(
            Long memberId,
            String content
    ) {
    }

    public record CommentUpdateReqDTO(
            String content
    ) {
    }
}
