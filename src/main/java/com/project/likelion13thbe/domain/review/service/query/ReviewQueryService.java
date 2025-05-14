package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewQueryService {

    // 리뷰 단일 조회
    ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId);

    ReviewResDTO.ReviewCursorResDTO getMyReviewsCursor(Long memberId,Long cursor, Integer size);

    ReviewResDTO.ReviewCursorResDTO getReviewsCursor(Long productId,Long cursor, Integer size);
}
