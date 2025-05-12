package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name="Review", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewQueryService reviewQueryService;
    private final ReviewCommandService reviewCommandService;

    @Operation(description = "리뷰 단건 조회")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/reviews/{reviewId}")
    public CustomResponse<ReviewResDTO.ReviewPreviewResDTO> getReview(@PathVariable("reviewId") @NotNull Long reviewId) {
        return CustomResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }

    @Operation(description = "리뷰 목록 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/api/v1/products/{productId}/reviews")
    public CustomResponse<ReviewResDTO.ReviewListDTO> getReviews(@PathVariable("productId") @NotNull Long productId) {
        return CustomResponse.onSuccess(reviewQueryService.getReviewList(productId));
    }

    @Operation(description = "리뷰 생성")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @PostMapping("/api/v1/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long productId,
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO dto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(productId, dto));
    }
    @Operation(description = "리뷰 수정")
    @Parameter(name = "reviewId", description = "review PK", example = "2")
    @PatchMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewPreviewResDTO> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.UpdateReviewDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
    @Operation(description = "리뷰 삭제")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/api/v1/reviews/my")
    public ResponseEntity<ReviewResDTO.ReviewListDTO> getMyReview() {
        return ResponseEntity.ok(reviewQueryService.getMyReview());
    }
}
