package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommentReqDTO {

    @Builder
    public record commentCreateReqDTO(
            String content
    ) {
    }

    @Builder
    public record commentUpdateReqDTO(
            String content
    ) {
    }
}
