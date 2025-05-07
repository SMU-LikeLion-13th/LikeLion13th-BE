package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewCommandService {
    public ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Long productId);
}
