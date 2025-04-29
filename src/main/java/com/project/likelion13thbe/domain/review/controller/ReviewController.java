package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name="Review",description = "리뷰 API")
public class ReviewController {

    @Operation(summary = "리뷰단일조회")
    @GetMapping("/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO getReview(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping("/products/{productid}/reviews")
    public  ReviewResDTO.ReviewListResponseDTO getReviewsList(@PathVariable Long productId) {
        return null;
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping("/users/{userId}/reviews")
    public ReviewResDTO.ReviewListResponseDTO getMyReviewsList(@PathVariable Long userId) {
        return null;
    }

    @Operation(summary = "리뷰 작성")
    @PostMapping("/product/{productId}/reviews/{reviewId}")
    public ReviewResDTO.ReviewCreateDTO PostReview(@PathVariable Long productId ) {
        return null;
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO getReviews(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("/reviews/{reviewId}")
    public ReviewResDTO.ReviewUpdateDTO PatchReviewUpdate(@PathVariable Long reviewId) {
        return null;
    }


}
