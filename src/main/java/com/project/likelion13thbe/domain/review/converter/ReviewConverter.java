package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {
        return Review.builder()
                .content(reviewCreateRequestDTO.content())
                .image(reviewCreateRequestDTO.image())
                .build();
    }

    public static ReviewResponseDTO.ReviewCreateResDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewCreateResDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewResDTO toReviewPreviewResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewResDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewResDTO toReviewResDTO(Review review) {
        return ReviewResponseDTO.ReviewResDTO.builder()
                .reviewId(review.getReviewId())
                .content(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewListResponseDTO toReviewListResponseDTO(List<Review> reviews) {
        List<ReviewResponseDTO.ReviewResDTO> reviewResDTOs = reviews.stream()
                .map(ReviewConverter::toReviewResDTO)
                .collect(Collectors.toList());

        return ReviewResponseDTO.ReviewListResponseDTO.builder()
                .reviews(reviewResDTOs)
                .build();
    }


}
