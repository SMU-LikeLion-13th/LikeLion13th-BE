package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        Member member = memberRepository.findById(reviewCreateReqDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        Product product = productRepository.findById(reviewCreateReqDTO.productId())
                .orElseThrow(() -> new IllegalArgumentException("상품 없음"));

        Review review = ReviewConverter.toReview(reviewCreateReqDTO, member, product);

        reviewRepository.save(review);

        return ReviewConverter.toReviewResDTO(review);


    }
}
