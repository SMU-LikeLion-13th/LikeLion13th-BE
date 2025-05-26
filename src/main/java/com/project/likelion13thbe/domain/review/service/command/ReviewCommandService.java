package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;

public interface ReviewCommandService {
    ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO dto);
    void deleteReview(Long id);
    void updateReview(Long id, ReviewReqDTO.ReviewUpdateReqDTO dto);
    void updateReviewByMemberIdAndId(Long memberId, Long id, ReviewReqDTO.ReviewUpdateReqDTO dto);
    void deleteReviewByMemberId(Long memberId);
}
