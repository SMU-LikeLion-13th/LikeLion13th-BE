package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Member member, Product product) {
        return Review.builder()
                .rating(reviewCreateReqDTO.rating())
                .content(reviewCreateReqDTO.content())
                .member(member)
                .product(product)
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewCreateResDTO(Review review) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .reviewId(review.getReviewId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewDetailResDTO toReviewDetailResDTO(Review review) {
        return ReviewResDTO.ReviewDetailResDTO.builder()
                .productId(review.getProduct().getProductId())
                .reviewId(review.getReviewId())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .nickname(review.getMember().getNickname())
                .profileImage(review.getMember().getProfileImage())
                .build();
    }
}
