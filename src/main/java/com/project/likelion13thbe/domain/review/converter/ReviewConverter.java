package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {
    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return Review.builder()
                .content(reviewCreateReqDTO.getContent())
                .starRating(reviewCreateReqDTO.getStarRating())
                .likeCount(reviewCreateReqDTO.getLikeCount())
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewResDTO(Review review) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
