package com.project.likelion13thbe.domain.review.convert;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConvert {
    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Member member) {
        return Review.builder()
                .description(reviewCreateReqDTO.description())
                .rating(reviewCreateReqDTO.rating())
                .profileImage(member.getImage())
                .member(member)
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewResDTO(Review review) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewDetailResDTO toReviewDetailResponse(Review review) {
        return ReviewResDTO.ReviewDetailResDTO.builder()
                .reviewId(review.getId())
                .description(review.getDescription())
                .rating(review.getRating())
                .profileImage(review.getProfileImage())
                .createdAt(review.getCreatedAt())
                .name(review.getMember().getName())
                .profileImage(review.getMember().getImage())
                .build();
    }
}
