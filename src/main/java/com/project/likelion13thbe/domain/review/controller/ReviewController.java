package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    @Operation(summary = "리뷰 상세 조회 API", description = "리뷰 상세 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "리뷰 상세 조회 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 상세 조회 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")

    @GetMapping("/api/v1/reviews/{reviewId}")
    public ReviewResponseDTO.ReviewListResponseDTO getReview(@PathVariable Long reviewId) {

        return null;
    }

    @Operation(summary = "리뷰 목록 조회 API", description = "리뷰 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "리뷰 목록 조회 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 목록 조회 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name="productId", description = "상품 아이디", example = "1")
    @GetMapping("api/v1/products/{productId}/reviews")
    public ReviewResponseDTO.ReviewListResponseDTO getReviews(@PathVariable Long productId) {
        return null;
    }

    @Operation(summary = "리뷰 수정 API", description = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "리뷰 수정 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 수정 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )

    })
    @Parameter(name="productId", description = "상품 아이디", example = "1")
    @PatchMapping("/api/v1/reviews/{reviewId}")
    public ReviewResponseDTO.ReviewListResponseDTO updateReview(
            @PathVariable Long productId,
            @RequestBody ReviewRequestDTO.ReviewListRequestDTO requestDTO
            ) {
        return null;
    }

    @Operation(summary = "리뷰 생성 API", description = "리뷰 생성")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "리뷰 생성 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 생성 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name = "productId", description = "상품 아이디", example = "1")
    @PostMapping("/api/v1/users/{userId}/products/{productId}/reviews")
    public ReviewResponseDTO.ReviewListResponseDTO postReview(
            @PathVariable Long productId,
            @RequestBody ReviewRequestDTO.ReviewListRequestDTO requestDTO
    ) {
        return null;
    }

    @Operation(summary = "리뷰 삭제 API", description = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "리뷰 삭제 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "리뷰 삭제 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1"),
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public ReviewResponseDTO.ReviewListResponseDTO deleteReview(@PathVariable Long productId, @PathVariable Long reviewId) {
        return null;
    }





}
