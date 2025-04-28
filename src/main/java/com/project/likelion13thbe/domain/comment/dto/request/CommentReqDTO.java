package com.project.likelion13thbe.domain.comment.dto.request;

import lombok.Builder;

public class CommentReqDTO {

    @Builder
    public record createCommentReqDTO(
            String content
    ) {
    }

}
