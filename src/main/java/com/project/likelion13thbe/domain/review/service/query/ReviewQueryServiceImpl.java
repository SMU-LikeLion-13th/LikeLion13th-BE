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
    public ReviewResDTO.ReviewPreviewResDTO getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();

        return ReviewConverter.toReviewPreviewResponseDTO(review);
    }

    @Override
    public ReviewResDTO.ReviewListDTO getReviewList(Long productId) {
        List<Review> reviews = reviewRepository.findAllByProductId(productId);

        return ReviewConverter.toReviewPreviewResponseDTOList(reviews);
    }

}
