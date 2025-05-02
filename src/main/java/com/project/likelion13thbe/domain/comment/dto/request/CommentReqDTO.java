package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommentReqDTO {

    @Builder
    public record commentCreateReqDTO(
    public record CommentCreateReqDTO(
            String content
    ) {
    }

    @Builder
    public record CommentUpdateReqDTO(
            String content
    ) {
    }
}
