package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    public CustomResponse<ReviewResDTO.ReviewPreviewResDTO> getReview(@PathVariable Long reviewId) {
        return CustomResponse.onSuccess(reviewQueryService.getReview(reviewId));
    }

    @Operation(summary = "리뷰 목록 조회")
    @GetMapping("/products/{productId}/reviews")
    public CustomResponse<ReviewResDTO.ReviewCursorResDTO> getReviews(
            @PathVariable Long productId,
            @RequestParam Long cursor,
            @RequestParam Integer size) {
        return CustomResponse.onSuccess(reviewQueryService.getMyReviewsCursor(productId,cursor, size));
    }

    @Operation(summary = "내 리뷰목록 조회")
    @GetMapping("/users/{userId}/reviews")
    public CustomResponse<ReviewResDTO.ReviewCursorResDTO> getMyReviewsCursor(
            @PathVariable Long userId,
            @RequestParam Long cursor,
            @RequestParam Integer size) {
        return CustomResponse.onSuccess(reviewQueryService.getMyReviewsCursor(userId,cursor, size));
    }


    @Operation(summary = "리뷰 생성")
    @PostMapping("/product/{productId}/reviews")
    public CustomResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable Long productId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateReqDTO dto){
        return CustomResponse.onSuccess(reviewCommandService.createReview(productId, dto));
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/reviews/{reviewId}")
    public CustomResponse<String> deleteReview(@PathVariable Long reviewId) {
        reviewCommandService.deleteReview(reviewId);
        return CustomResponse.onSuccess("리뷰 삭제 성공");
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("/reviews/{reviewId}")
    public CustomResponse<ReviewResDTO.ReviewPreviewResDTO> updateReview(
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewReqDTO.ReviewUpdateReqDTO dto //클라이언트로부터 리뷰 수정 요청 데이터를 받기 위한 DTO.
    ) {
        return CustomResponse.onSuccess(reviewCommandService.updateReview(reviewId, dto)); //서비스 계층에서 리뷰 수정 로직을 수행.
    }


}
