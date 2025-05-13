package com.project.likelion13thbe.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

public class CommentReqDTO {
    @Builder
    public record CommentCreateReqDTO(
            String description,
            Long memberId,
            Long reviewId
    ) {}
    @Getter
    public static class CommentUpdateDTO{
        @Schema(description="댓글 수정", example = "new 댓글")
        String description;

    }
}
