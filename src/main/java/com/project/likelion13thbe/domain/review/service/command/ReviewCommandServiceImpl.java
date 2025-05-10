package com.project.likelion13thbe.domain.review.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.exception.ProductErrorCode;
import com.project.likelion13thbe.domain.product.exception.ProductException;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.domain.review.converter.ReviewConverter;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public ReviewResponseDTO.ReviewCreateResDTO createReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {
        // DTO -> Review
        Review review = ReviewConverter.toReview(reviewCreateRequestDTO);

        Member member = memberRepository.findById(reviewCreateRequestDTO.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Product product = productRepository.findById(reviewCreateRequestDTO.productId())
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_CODE));

        // 연관 관계 설정
        review.setMember(member);
        review.setProduct(product);

        // Review 엔티티 DB에 저장
        reviewRepository.save(review);

        // 응답 DTO로 변환하고 return
        return ReviewConverter.toReviewResponseDTO(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findByIdNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_CODE));

        review.delete();
    }

    @Override
    public void updateReview(Long reviewId, ReviewRequestDTO.ReviewUpdateRequestDTO dto) {
        Review review = reviewRepository.findByIdNotDeleted(reviewId).orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_CODE));

        review.update(dto.content());
    }
}

