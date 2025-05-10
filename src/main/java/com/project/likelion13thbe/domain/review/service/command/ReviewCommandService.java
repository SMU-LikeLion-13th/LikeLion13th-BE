package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewCommandService {
    public ReviewResponseDTO.ReviewCreateResDTO createReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO);

    void deleteReview(Long id);

    void updateReview(Long reviewId, ReviewRequestDTO.ReviewUpdateRequestDTO dto);
}
