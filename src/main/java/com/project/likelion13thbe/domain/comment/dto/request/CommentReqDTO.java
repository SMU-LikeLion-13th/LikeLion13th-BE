package com.project.likelion13thbe.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CommentReqDTO {

    public record CommentCreateReqDTO(
            Long memberId,
            @NotBlank(message = "댓글 내용은 필수 입력값입니다.")
            String content
    ) {
    }

    public record CommentUpdateReqDTO(
            String content
    ) {
    }
}
