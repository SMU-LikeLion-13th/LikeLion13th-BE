package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewEception;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
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

    @Override
    public ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewEception(ReviewErrorCode.REVIEW_NOT_FOUND));

        return ReviewConverter.toReviewPreviewResponseDTO(review);
    }



    @Override
    public ReviewResDTO.ReviewCursorResDTO getMyReviewsCursor(Long memberId, Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Review> reviews = reviewRepository.findByMemberIdAndIdLessThanOrderByCreatedAtDesc(memberId, cursor, pageable);
        if (reviews.isEmpty()) {
            throw new ReviewEception(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return ReviewConverter.toReviewCursorResDTO(reviews);
    }


    @Override
    public ReviewResDTO.ReviewCursorResDTO getReviewsCursor(Long productId, Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Review> reviews = reviewRepository.findByProductIdAndIdLessThanOrderByCreatedAtDesc(productId, cursor, pageable);

        if (reviews.isEmpty()) {
            throw new ReviewEception(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        return ReviewConverter.toReviewCursorResDTO(reviews);
    }

}
