package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommentReqDTO {

    @Builder
    public record CommentCreateReqDTO(
            Long memberId,
            String content
    ) {
    }

    @Builder
    public record CommentUpdateReqDTO(
            String content
    ) {
    }
}
