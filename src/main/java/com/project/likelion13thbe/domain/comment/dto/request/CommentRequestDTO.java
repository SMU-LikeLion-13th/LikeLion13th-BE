package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommentRequestDTO {

    @Builder
    public record CommentCreateRequestDTO(
            String content
    ) {
    }

    @Builder
    public record CommentUpdateRequestDTO(
            String content
    ) {
    }

}
