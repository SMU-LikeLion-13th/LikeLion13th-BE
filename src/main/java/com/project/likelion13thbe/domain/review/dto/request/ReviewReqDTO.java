package com.project.likelion13thbe.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    public static class ReviewCreateReqDTO {
        private String content;
        private String starRating;
        private String likeCount;
    }
    @Getter
    public static class ReviewEditDTO {
        @Schema(description = "리뷰 수정", example = "소재가 좋습니다.")
        @NotBlank(message = "빈 리뷰를 등록할 수 없습니다.")
        private String content;
    }

    @Getter
    public static class ReviewDeleteDTO {

    }
}
