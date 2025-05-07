package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewConverter {

    public static Review toReview(
            ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO,
            Member member,
            Product product) {
        return Review.builder()
                .content(reviewCreateReqDTO.content())
                .date(reviewCreateReqDTO.date())
                .member(member)
                .product(product)
                .build();
    }

    public static ReviewResDTO.ReviewCreateResDTO toReviewResDTO(Review review) {
        return new ReviewResDTO.ReviewCreateResDTO(
                review.getReviewId(),
                review.getCreatedAt()
        );
    }

    public static ReviewResDTO.ReviewPreviewResDTO toReviewPreviewResDTO(Review review) {
        return new ReviewResDTO.ReviewPreviewResDTO(
                review.getContent(),
                review.getDate(),
                review.getMember().getUserId(),
                review.getProduct().getProductId()
        );
    }

    public static ReviewResDTO.ReviewOffsetResDTO toReviewOffsetResDTO(Page<Review> page) {
        List<ReviewResDTO.ReviewPreviewResDTO> reviews =
                page.getContent().stream()
                        .map(ReviewConverter::toReviewPreviewResDTO)
                        .toList();

        return new ReviewResDTO.ReviewOffsetResDTO(
                reviews,
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public static ReviewResDTO.ReviewCursorResDTO toReviewCursorResDTO(Slice<Review> reviews) {
        List<ReviewResDTO.ReviewPreviewResDTO> reviewList = reviews.stream()
                .map(ReviewConverter::toReviewPreviewResDTO)
                .toList();

        Boolean hasNext = reviews.hasNext();

        Long nextCursor = null;
        if (!reviews.isEmpty() && reviews.hasNext()) {
            nextCursor = reviews.getContent().get(reviews.getNumberOfElements() - 1).getReviewId();
        }

        return new ReviewResDTO.ReviewCursorResDTO(
                reviewList,
                nextCursor,
                hasNext
        );

    }
}
