package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO);

    void updateReview(Long reviewId, ReviewReqDTO.ReviewUpdateDTO reviewUpdateDTO);

    void deleteReview(Long reviewId);
}
