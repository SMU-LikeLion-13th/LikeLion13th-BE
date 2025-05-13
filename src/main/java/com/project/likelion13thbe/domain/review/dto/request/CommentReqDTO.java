package com.project.likelion13thbe.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class CommentReqDTO {

    public record CommentCreateReqDTO(
            String content,
            LocalDateTime date,
            Long memberId,
            Long reviewId
    ) {}

    public record CommentUpdateReqDTO (
            @Schema(description = "수정할 내용", example = "이렇게 수정할 예정")
            @NotBlank(message = "변경사항은 필수 입력값입니다.")
            String content
    ) {}

    public record CommentDeleteReqDTO (
            Long commentId
    ) {}


}
