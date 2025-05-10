package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO.ReviewCreateResDTO createReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {
        // DTO -> Review
        Review review = ReviewConverter.toReview(reviewCreateRequestDTO);

        // Review 엔티티 DB에 저장
        reviewRepository.save(review);

        // 응답 DTO로 변환하고 return
        return ReviewConverter.toReviewResponseDTO(review);
    }
}

