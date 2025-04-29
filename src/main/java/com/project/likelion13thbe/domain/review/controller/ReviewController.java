package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Review", description = "리뷰 관련 API")
public class ReviewController {
    @Operation(description = "리뷰 단건 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "Ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewDTO.class)
                    )
            )
    })
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewDTO> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "리뷰 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "Ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewListDTO.class)
                    )
            )
    })
    @Parameter(name = "productId", description = "product PK", example = "1")
    @GetMapping("/api/v1/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewListDTO> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "리뷰 생성")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @PostMapping("/api/v1/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewDTO> createReview(
            @PathVariable Long productId,
            @RequestBody ReviewReqDTO.CreateReviewDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
    @Operation(description = "리뷰 수정")
    @Parameter(name = "reviewId", description = "review PK", example = "2")
    @PatchMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewDTO> updateReview(
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
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/reviews/my")
    public ResponseEntity<ReviewResDTO.ReviewDTO> getMyReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(null);
    }
}
