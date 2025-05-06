package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override // 리뷰 단일 조회
    public ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();

        return ReviewConverter.toReviewPreviewResponseDTO(review);
    }



    @Override
    public ReviewResDTO.ReviewCursorResDTO getMyReviewsCursor(Long memberId, Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Review> reviews = reviewRepository.findByMemberIdOrderByCreatedAtDesc(memberId, pageable);

        return ReviewConverter.toReviewCursorResDTO(reviews);
    }


}
