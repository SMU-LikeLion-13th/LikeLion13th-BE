package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewQueryService {
    ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId);

    //커서 기반 내 리뷰목록 조회
    ReviewResDTO.ReviewCursorResDTO getMyReviewsCursor(Long memberId, Long cursor, Integer size);
}
