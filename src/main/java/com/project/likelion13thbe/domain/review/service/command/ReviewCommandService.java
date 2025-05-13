package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewCommandService {
    ReviewResDTO.ReviewCreateResDTO createReview(Long productId, ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO);

    ReviewResDTO.ReviewPreviewResDTO updateReview(Long ReviewId, ReviewReqDTO.ReviewUpdateReqDTO reviewUpdateReqDTO);

    void deleteReview(Long reviewId);
}
