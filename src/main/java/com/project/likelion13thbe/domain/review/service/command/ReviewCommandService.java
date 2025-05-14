package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewCommandService {

    ReviewResponseDTO.ReviewCreateResponseDTO createReview(Long productId, ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO);

    void updateReview(Long reviewId, ReviewRequestDTO.ReviewUpdateRequestDTO reviewUpdateRequestDTO);

    void deleteReview(Long reviewId);
}
