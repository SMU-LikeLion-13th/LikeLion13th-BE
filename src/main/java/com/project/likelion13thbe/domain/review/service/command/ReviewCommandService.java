package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewCreateResDTO createReview(Long productId, ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO);

    ReviewResDTO.ReviewPreviewResDTO updateReview(Long reviewId, ReviewReqDTO.ReviewUpdateReqDTO reviewUpdateReqDTO);
}
