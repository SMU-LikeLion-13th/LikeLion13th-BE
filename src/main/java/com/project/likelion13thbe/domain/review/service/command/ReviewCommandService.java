package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;

public interface ReviewCommandService {

    ReviewResponseDTO.ReviewCreateResponseDTO createReview(Long productId, ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO, Member member);

    void updateReview(Long reviewId, ReviewRequestDTO.ReviewUpdateRequestDTO reviewUpdateRequestDTO, Member member);

    void deleteReview(Long reviewId, Member member);
}
