package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewQueryService {

    ReviewResponseDTO.ReviewDetailResponseDTO getReview(Long reviewId);

    ReviewResponseDTO.ReviewListResponseDTO getReviews();

    ReviewResponseDTO.ReviewCursorResponseDTO getReviewCursor(Long productId, Long cursor, Integer size);
}
