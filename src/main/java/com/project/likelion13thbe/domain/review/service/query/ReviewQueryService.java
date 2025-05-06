package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewQueryService {

    ReviewResponseDTO.ReviewDetailResponseDTO getReview(Long reviewId);

}
