package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    @Override
    public ReviewResponseDTO.ReviewPreviewResDTO getReview(){
        // DB에서 pk가 1인 Review 조회
        Review review = reviewRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return ReviewConverter.toReviewPreviewResponseDTO(review);
    }

    @Override
    public ReviewResponseDTO.ReviewListResponseDTO getReviewList(){
        List<Review> review = reviewRepository.findAll();

        return ReviewConverter.toReviewListResponseDTO(review);

    }

    @Override
    public ReviewResponseDTO.ReviewOffsetResponseDTO getReviewOffset(Integer offset, Integer size){
        Pageable pageable = PageRequest.of(offset-1, size);
        Page<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc(pageable);
        return ReviewConverter.toReviewOffsetResponseDTO(reviews);
    }
}
