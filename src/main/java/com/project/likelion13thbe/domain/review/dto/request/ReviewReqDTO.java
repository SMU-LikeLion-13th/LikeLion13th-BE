package com.project.likelion13thbe.domain.review.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewReqDTO {

    public record ReviewCreateReqDTO(
            String content,
            LocalDateTime date,
            Long memberId,
            Long productId
    ) {}

    public record ReviewUpdateReqDTO (
            @Schema(description = "수정할 내용", example = "이렇게 바꿀 예정")
            @NotBlank(message = "변경사항은 필수 입력값입니다.")
            String content
    ){}

    public record ReviewDeleteReqDTO(
            Long reviewId
    ) {}


}
