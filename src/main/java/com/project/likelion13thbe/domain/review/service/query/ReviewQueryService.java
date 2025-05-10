package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewQueryService {
    public ReviewResponseDTO.ReviewPreviewResDTO getReview();

    public ReviewResponseDTO.ReviewListResponseDTO getReviewList();
}
