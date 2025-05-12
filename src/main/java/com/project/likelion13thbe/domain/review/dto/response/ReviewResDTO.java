package com.project.likelion13thbe.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public class ReviewResDTO {

    @Builder
    public record ReviewDetailResDTO(
            Long productId,
            Long reviewId,
            Double rating,
            String content,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String nickname,
            String profileImage
    ) {
    }

    @Builder
    public record ReviewListResDTO(
            List<ReviewDetailResDTO> reviewList
    ) {
    }

    @Builder
    public record ReviewCreateResDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewCursorResDTO(
            List<ReviewResDTO.ReviewDetailResDTO> reviews,
            Long nextCursor,
            Boolean hasNext
    ) {
    }

}
