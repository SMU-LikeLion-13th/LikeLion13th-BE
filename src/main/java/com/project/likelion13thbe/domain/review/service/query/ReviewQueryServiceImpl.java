package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.convert.ReviewConvert;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewDetailResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없음."));

        return ReviewConvert.toReviewDetailResponse(review);
    }

    @Override
    public ReviewResDTO.ReviewListResDTO getReviews() {
        List<ReviewResDTO.ReviewDetailResDTO> reviews = reviewRepository.findAll().stream()
                .map(ReviewConvert::toReviewDetailResponse).toList();

        return ReviewResDTO.ReviewListResDTO.builder().reviewList(reviews).build();
    }
}
