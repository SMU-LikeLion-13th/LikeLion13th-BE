package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {


    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        return ReviewConverter.toReviewPreviewResponseDTO(review);
    }

    @Override
    public ReviewResDTO.ReviewListDTO getReviewList(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);

        if (reviews.isEmpty())
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);

        return ReviewConverter.toReviewPreviewResponseDTOList(reviews);
    }

    @Override
    public ReviewResDTO.ReviewListDTO getMyReview(String email) {
        List<Review> reviews = reviewRepository.findAllByEmail(email);

        if (reviews.isEmpty()){
            throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
        }

        for (Review review : reviews) {
            if (!review.getMember().getEmail().equals(email)) {
                throw new ReviewException(ReviewErrorCode.REVIEW_ACCESS_DENIED);
            }
        }

        return ReviewConverter.toReviewPreviewResponseDTOList(reviews);
    }
}
