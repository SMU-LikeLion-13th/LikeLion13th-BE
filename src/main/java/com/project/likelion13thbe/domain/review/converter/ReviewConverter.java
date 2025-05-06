package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {
    public static Review toReview(ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO, Product product, Member member) {
        return Review.builder()
                .content(reviewCreateReqDTO.content())
                .score(reviewCreateReqDTO.score())
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

    //리뷰 단일 조회
    public static ReviewResDTO.ReviewPreviewResDTO toReviewPreviewResponseDTO(Review review) {
        return ReviewResDTO.ReviewPreviewResDTO.builder()
                .id(review.getId())
                .username(review.getMember().getName())
                .image(review.getMember().getImage())
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }

    //목록 조회
    public static ReviewResDTO.ReviewCursorResDTO toReviewCursorResDTO(Slice<Review> reviews) {
        List<ReviewResDTO.ReviewPreviewResDTO> reviewList = reviews.getContent().stream()
                .map(ReviewConverter::toReviewPreviewResponseDTO)
                .toList();

        // 다음 cursor 지정
        Long nextCursor = null;
        if (!reviews.isEmpty() && reviews.hasNext()) {
            nextCursor = reviews.getContent().get(reviews.getNumberOfElements() - 1).getId();
        }

        return ReviewResDTO.ReviewCursorResDTO.builder()
                .reviews(reviewList)
                .hasNext(reviews.hasNext())
                .nextCursor(nextCursor)
                .build();
    }

}
