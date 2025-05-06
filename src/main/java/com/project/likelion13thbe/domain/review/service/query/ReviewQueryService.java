package com.project.likelion13thbe.domain.review.service.query;

import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public ReviewResDTO.ReviewPreviewResDTO getReview() {

        Review review = reviewRepository.findById(1L).get();

        return ReviewConverter.toReviewPreviewResDTO(review);
    }

    public ReviewResDTO.ReviewOffsetResDTO getReviewOffset(Integer offset, Integer size) {
        Pageable pageable = PageRequest.of(offset - 1, size);

        Page<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc(pageable);

        return ReviewConverter.toReviewOffsetResDTO(reviews);
    }

    public ReviewResDTO.ReviewCursorResDTO getReviewCursor(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE;
        }

        Slice<Review> reviews = reviewRepository.findAllByReviewIdLessThanOrderByReviewIdDesc(cursor, pageable);

        return ReviewConverter.toReviewCursorResDTO(reviews);
    }

}
