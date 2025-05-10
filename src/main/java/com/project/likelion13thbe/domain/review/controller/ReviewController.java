package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandServiceImpl;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("/api/v1/")
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {
    private final ReviewCommandServiceImpl reviewCommandService;
    private final ReviewQueryServiceImpl reviewQueryService;

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

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.ReviewPreviewResDTO> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewQueryService.getReview());
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
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponseDTO> getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewQueryService.getReviewList());
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
    @PatchMapping("/reviews/{reviewId}")
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
    @PostMapping("/users/{userId}/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewCreateResDTO> postReview(
            @PathVariable Long productId,
            @RequestBody ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateRequestDTO));
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
    @DeleteMapping("/reviews/{reviewId}")
    public ReviewResponseDTO.ReviewListResponseDTO deleteReview(@PathVariable Long reviewId) {
        return null;
    }





}
