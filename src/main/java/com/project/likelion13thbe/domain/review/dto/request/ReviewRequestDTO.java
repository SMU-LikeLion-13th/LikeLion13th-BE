package com.project.likelion13thbe.domain.review.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewListRequestDTO {
        @Schema(description = "리뷰 목록")
        private List<ReviewReqDTO> reviews;
    }

    @Getter
    public static class ReviewReqDTO {
        @Schema(description = "리뷰 내용",example = "상품이 좋아요.")
        private String content;
    }

    public record ReviewCreateRequestDTO(
            String content,
            Long rating,
            String image
    ) {}

}
