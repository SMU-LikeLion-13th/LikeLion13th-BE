package com.project.likelion13thbe.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    @Builder
    public record ReviewDetailResponseDTO(
            Long reviewId,
            String content,
            String image,
            Integer rating,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            String nickname,
            String profileImage
    ) {
    }

    @Builder
    public record ReviewListResponseDTO(
            List<ReviewDetailResponseDTO> reviewList
    ) {
    }

    @Builder
    public record ReviewCreateResponseDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {
    }

    @Builder
    public record ReviewCursorResponseDTO(
            List<ReviewDetailResponseDTO> reviews,
            Long nextCursor,
            Boolean hasNext
    ) {
    }
}
