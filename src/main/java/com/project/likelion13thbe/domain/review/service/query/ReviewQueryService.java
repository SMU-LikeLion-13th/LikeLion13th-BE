package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewDetailResDTO getReview(Long reviewId);

    ReviewResDTO.ReviewListResDTO getReviewList(Long productId);

    ReviewResDTO.ReviewListResDTO getMyReviewList();

    ReviewResDTO.ReviewCursorResDTO getReviewCursor(Long productId, Long cursor, Integer size);
}
