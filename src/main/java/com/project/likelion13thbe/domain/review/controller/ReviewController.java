package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name="Review",description = "리뷰 API")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;


    public ReviewController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @Operation(summary = "리뷰 단일 조회")
    @GetMapping("/reviews/{reviewId}")
    public CustomResponse<ReviewResDTO.ReviewPreviewResDTO> getReview(@PathVariable("reviewId")@NotNull Long reviewId) {
        return CustomResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCursorResDTO> getReviews(
            @PathVariable Long productId,
            @RequestParam Long cursor,
            @RequestParam Integer size) {
        return ResponseEntity.ok(reviewQueryService.getMyReviewsCursor(productId,cursor, size));
    }

    @Operation(summary = "내 리뷰목록 조회")
    @GetMapping("/users/{userId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCursorResDTO> getMyReviewsCursor(
            @PathVariable Long userId,
            @RequestParam Long cursor,
            @RequestParam Integer size) {
        return ResponseEntity.ok(reviewQueryService.getMyReviewsCursor(userId,cursor, size));
    }


    @Operation(summary = "리뷰 생성")
    @PostMapping("/product/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long productId,
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(productId, dto));
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO getReviews(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("/reviews/{reviewId}")
    public ReviewResDTO.ReviewUpdateDTO patchReviewUpdate(@PathVariable Long reviewId) {
        return null;
    }


}
