package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Review API", description = "리뷰 관련 API입니다.")
public class ReviewController {

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
