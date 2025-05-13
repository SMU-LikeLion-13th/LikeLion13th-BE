package com.project.likelion13thbe.domain.comment.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class CommentReqDTO {

    @Builder
    public record CommentCreateRequestDTO(
            Long memberId,
            Long reviewId,
            String content
    ) {
    }

    @Builder
    public record CommentUpdateReqDTO(
            @NotBlank(message = "내용은 필수로 입력해야 합니다.")
            String content
    ){
    }

}
