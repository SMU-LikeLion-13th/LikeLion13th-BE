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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Override
    public ReviewResDTO.ReviewCreateResDTO createReview(Long productId, ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        Member member = memberRepository.findById(reviewCreateReqDTO.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductErrorCode.PRODUCT_NOT_FOUND));

        Review review = ReviewConverter.toReview(reviewCreateReqDTO, product, member);

        reviewRepository.save(review);

        return ReviewConverter.toReviewResponseDTO(review);
    }
}
