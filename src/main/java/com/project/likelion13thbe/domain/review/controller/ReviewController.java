package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.service.command.OrderCommandService;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Review API", description = "리뷰 관련 API입니다.")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(CreateReqDTO.reviewCreateReqDTO));
    }

    @Operation(summary = "단건 리뷰 조회 API")
    @ApiResponses({
        @ApiResponse(
            responseCode="COMMON200", description="OK, 성공",
            content = @Content(mediaType = "application/json",
            schema= @Schema(implementation = ReviewResDTO.ReviewResponseDTO.class)
            )
        )
    })
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO getReview(
            @PathVariable Long reviewId
    ) {
        return null;
    }

    @Operation(summary = "리뷰 목록 조회 API")
    @Parameter(name = "productId", description = "review PK", example = "1")
    @GetMapping("/api/v1/{productId}/reviews")
    public ReviewResDTO.ReviewResponseDTO getReviewsList(
            @PathVariable Long productId
    ) {
        return null;
    }

    @Operation(summary = "리뷰 생성 API")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @PostMapping("/api/v1/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO addReview(
            @PathVariable Long reviewId
    ) {
        return null;
    }

    @Operation(summary = "리뷰 수정 API")
    @PutMapping("/api/v1/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO updateReview(
            @PathVariable Long reviewId
    ) {
        return null;
    }

    @Operation(summary = "리뷰 삭제 API")
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public ReviewResDTO.ReviewResponseDTO deleteReview(
            @PathVariable Long reviewId
    ) {
        return null;
    }
}
