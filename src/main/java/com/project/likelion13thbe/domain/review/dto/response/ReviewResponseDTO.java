package com.project.likelion13thbe.domain.review.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewListResponseDTO {
        @Schema(description = "리뷰 목록")
        private List<ReviewResDTO> reviews;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewResDTO {
        @Schema(description = "review의 pk",example = "1")
        private Long reviewId;
        @Schema(description = "리뷰 내용", example = "상품 훌륭")
        private String content;
    }


}
