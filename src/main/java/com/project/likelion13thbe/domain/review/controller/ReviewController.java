package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    @Operation(summary = "리뷰 단건 조회")
    @GetMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.ReviewDetailResponseDTO> getReview(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping("/api/v1/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponseDTO> getReviewList(@PathVariable Long productId) {
        return null;
    }
}
