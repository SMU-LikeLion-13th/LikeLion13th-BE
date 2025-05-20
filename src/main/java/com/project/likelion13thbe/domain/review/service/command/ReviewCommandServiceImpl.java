package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        Review review = ReviewConverter.toReview(reviewCreateReqDTO);

        reviewRepository.save(review);

        return ReviewConverter.toReviewResDTO(review);
    }

    public void DeleteReview(String content) {
        Review review = reviewRepository.findByIdAndNotDeleted(content).orElseThrow();
    }
}
