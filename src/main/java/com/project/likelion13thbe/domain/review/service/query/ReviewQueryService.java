package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewQueryService {
    public ReviewResDTO.ReviewDetailResDTO getReview(Long reviewId);

    public ReviewResDTO.ReviewListResDTO getReviewList(Long productId);
}
