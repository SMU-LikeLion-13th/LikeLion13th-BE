package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewDetailResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review가 존재하지 않음"));
        return ReviewConverter.toReviewDetailResDTO(review);
    }

    @Override
    public ReviewResDTO.ReviewListResDTO getReviewList(Long productId) {
        List<Review> reviewList = reviewRepository.findAllReviewsByProductId(productId);
        if (reviewList.isEmpty()) {
            throw new RuntimeException("review가 존재하지 않음");
        }

        List<ReviewResDTO.ReviewDetailResDTO> filteredReviewsDetailResDTOList =
                reviewList.stream()
                        .map(ReviewConverter::toReviewDetailResDTO)
                        .toList();

        return ReviewConverter.toReviewListResDTO(filteredReviewsDetailResDTOList);
    }

    @Override
    public ReviewResDTO.ReviewListResDTO getMyReviewList() {
        List<Review> reviewList = reviewRepository.findAllReviewsByMemberId(1L);
        // 이게 상품별 리뷰 조회는 Path Variable로 받아왔는데,
        // 내 리뷰는 아직 토큰 구별 기능 불가능 이슈로 상수 넣었습니다
        if (reviewList.isEmpty()) {
            throw new RuntimeException("review가 존재하지 않음");
        }

        List<ReviewResDTO.ReviewDetailResDTO> filteredReviewsDetailResDTOList =
                reviewList.stream()
                        .map(ReviewConverter::toReviewDetailResDTO)
                        .toList();

        return ReviewConverter.toReviewListResDTO(filteredReviewsDetailResDTOList);
    }

}
