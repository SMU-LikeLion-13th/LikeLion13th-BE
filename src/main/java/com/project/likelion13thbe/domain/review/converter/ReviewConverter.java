package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO dto, Member member, Product product) {
        return Review.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .rating(dto.getRating())
                .member(member)
                .product(product)
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewResDTO(Review review) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.ReviewDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .rating(review.getRating())
                .likeCount(review.getLikeCount())
                .build();
    }
}
