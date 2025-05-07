package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Product product, Member member) {
        return Review.builder()
                .content(reviewCreateReqDTO.content())
                .rate(reviewCreateReqDTO.rate())
                .product(product)
                .member(member)
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewResponseDTO(Review review) {
        return ReviewResDTO.ReviewCreateResDTO.builder()
                .id(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewPreviewResDTO toReviewPreviewResponseDTO(Review review) {
        return ReviewResDTO.ReviewPreviewResDTO.builder()
                .id(review.getId())
                .username(review.getMember().getName())
                .profileImg(review.getMember().getImage())
                .content(review.getContent())
                .rate(review.getRate())
                .createdAt(review.getCreatedAt())
                .build();
    }
    public static ReviewResDTO.ReviewListDTO toReviewPreviewResponseDTOList(List<Review> reviews) {
        return ReviewResDTO.ReviewListDTO.builder()
                .reviews(reviews.stream()
                        .map(ReviewConverter::toReviewPreviewResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
