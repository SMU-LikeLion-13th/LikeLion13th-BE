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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public ReviewResDTO.ReviewCreateResDTO createReview(ReviewReqDTO.ReviewCreateReqDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Review review = ReviewConverter.toReview(dto, member, product);

        reviewRepository.save(review);

        return ReviewConverter.toReviewResDTO(review);
    }

    public void updateReview(Long id, ReviewReqDTO.ReviewUpdateReqDTO dto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        if (dto.getTitle() != null) {
            review.setTitle(dto.getTitle());
        }
        if(dto.getContent() != null) {
            review.setContent(dto.getContent());
        }
        if(dto.getRating() != null) {
            review.setRating(dto.getRating());
        }
        if(dto.getLikeCount() != null) {
            review.setLikeCount(dto.getLikeCount());
        }
        if (dto.getMemberId() != null) {
            review.setMember(memberRepository.findById(dto.getMemberId()).orElseThrow());
        }
        if (dto.getProductId() != null) {
            review.setProduct(productRepository.findById(dto.getProductId()).orElseThrow());
        }
        reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        if(reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Review not found");
        }
    }

}
