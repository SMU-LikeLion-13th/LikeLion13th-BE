package com.project.likelion13thbe.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class CommentReqDTO {
    @Builder
    public record CommentCreateReqDTO(
            @NotBlank(message = "내용은 필수 입력값입니다.")
            String content
    ) {
    }

    @Builder
    public record CommentUpdateReqDTO(
            @NotBlank(message = "내용은 필수 입력값입니다.")
            String content
    ){
    }
}
