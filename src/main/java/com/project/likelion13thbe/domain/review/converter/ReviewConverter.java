package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
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

    public static ReviewResponseDTO.ReviewDetailResponseDTO toReviewDetailResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewDetailResponseDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .image(review.getImage())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .nickname(review.getMember().getName())
                .profileImage(review.getMember().getProfileImage())
                .build();
    }
}
