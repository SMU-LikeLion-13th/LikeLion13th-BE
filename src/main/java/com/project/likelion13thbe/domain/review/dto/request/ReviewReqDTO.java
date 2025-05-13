package com.project.likelion13thbe.domain.review.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record ReviewCreateReqDTO(
            @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다.")
            String content,

            @NotNull(message = "리뷰 평점은 필수 입력 항목입니다.")
            @Min(value = 0, message = "평점은 최소 0점 이상이어야 합니다.")
            @Max(value = 5, message = "평점은 최대 5점 이하이어야 합니다.")
            Double score,

            @NotNull(message = "회원 ID는 필수 입력 항목입니다.")
            Long memberId
    ) {
    }


}
