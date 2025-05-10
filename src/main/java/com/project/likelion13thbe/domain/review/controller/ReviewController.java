package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewRequestDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Review", description = "리뷰 관련 API")
@RequestMapping("/api/v1")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "리뷰 단건 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.ReviewDetailResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO.ReviewDetailResponseDTO> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewQueryService.getReview(reviewId));
    }

    @Operation(summary = "리뷰 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponseDTO> getReviewList(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewQueryService.getReviews());
    }

    @Operation(summary = "리뷰 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewResponseDTO.ReviewCreateResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
            content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewCreateResponseDTO> createReview(@PathVariable Long productId, @RequestBody ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateRequestDTO));
    }

    @Operation(summary = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<?> editReview(@PathVariable Long reviewId, @RequestBody ReviewRequestDTO.ReviewCreateRequestDTO reviewCreateRequestDTO) {
        return null;
    }

    @Operation(summary = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return null;
    }
}
