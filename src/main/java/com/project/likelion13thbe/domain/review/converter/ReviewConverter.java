package com.project.likelion13thbe.domain.review.converter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO, Member member) {
        return Review.builder()
                .content(reviewCreateRequestDTO.content())
                .rating(reviewCreateRequestDTO.rating())
                .image(reviewCreateRequestDTO.image())
                .member(member)
                .build();
    }

    public static ReviewResponseDTO.ReviewCreateResponseDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewCreateResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
