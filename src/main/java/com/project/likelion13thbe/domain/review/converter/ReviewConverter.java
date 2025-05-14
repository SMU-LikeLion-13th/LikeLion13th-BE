package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO, Member member, Product product) {
        return Review.builder()
                .content(reviewCreateRequestDTO.content())
                .rating(reviewCreateRequestDTO.rating())
                .image(reviewCreateRequestDTO.image())
                .member(member)
                .product(product)
                .build();
    }

    public static ReviewResponseDTO.ReviewCreateResponseDTO toReviewResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewCreateResponseDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResponseDTO.ReviewDetailResponseDTO toReviewDetailResponseDTO(Review review) {
        return ReviewResponseDTO.ReviewDetailResponseDTO.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .image(review.getImage())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .nickname(review.getMember().getName())
                .profileImage(review.getMember().getProfileImage())
                .build();
    }

    public static ReviewResponseDTO.ReviewCursorResponseDTO toReviewCursorResponseDTO(Slice<Review> reviews) {
        List<ReviewResponseDTO.ReviewDetailResponseDTO> reviewList = reviews.stream()
                .map(ReviewConverter::toReviewDetailResponseDTO)
                .toList();

        Long nextCursor = null;
        if (!reviews.isEmpty() && reviews.hasNext()) {
            nextCursor = reviews.getContent().get(reviews.getNumberOfElements() - 1).getId();
        }

        return ReviewResponseDTO.ReviewCursorResponseDTO.builder()
                .reviews(reviewList)
                .hasNext(reviews.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
