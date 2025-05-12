package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
//@RequestMapping("reviews") // 리뷰는 product가 있어서 안되네용....
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    @Operation(summary = "리뷰 세부 조회")
    @GetMapping("reviews/{reviewId}")
    public CustomResponse<ReviewResDTO.ReviewDetailResDTO> getReview(@PathVariable Long reviewId) {
        return CustomResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping("products/{productId}/reviews")
    public CustomResponse<ReviewResDTO.ReviewListResDTO> getReviewList(@PathVariable Long productId) {
        return CustomResponse.onSuccess(reviewQueryService.getReviewList(productId));
    }

    @Operation(summary = "리뷰 작성")
    @PostMapping("products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long productId, @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateReqDTO, productId));
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("reviews/{reviewId}")
    public ResponseEntity<?> patchReview(@PathVariable Long reviewId, @RequestBody ReviewReqDTO.ReviewUpdateReqDTO reviewUpdateReqDTO) {
        return null;
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping("/reviews/my")
    public CustomResponse<ReviewResDTO.ReviewListResDTO> getMyReviews() {
        return CustomResponse.onSuccess(reviewQueryService.getMyReviewList());
    }
}
