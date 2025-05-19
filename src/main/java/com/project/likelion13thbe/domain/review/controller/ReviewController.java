package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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
    public CustomResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @PathVariable("productId") Long productId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        return CustomResponse.onSuccess(reviewCommandService.createReview(productId, reviewCreateReqDTO, userDetails.getUsername()));
    }
    @Operation(description = "리뷰 수정")
    @Parameter(name = "reviewId", description = "review PK", example = "2")
    @PatchMapping("/api/v1/reviews/{reviewId}")
    public CustomResponse<ReviewResDTO.ReviewPreviewResDTO> updateReview(
        @PathVariable("reviewId") Long reviewId,
        @RequestBody @Valid ReviewReqDTO.ReviewUpdateReqDTO updateReviewDTO,
        @AuthenticationPrincipal UserDetails userDetails
    ) {
        return CustomResponse.onSuccess(reviewCommandService.updateReview(reviewId, updateReviewDTO, userDetails.getUsername()));
    }
    @Operation(description = "리뷰 삭제")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public CustomResponse<String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewCommandService.deleteReview(reviewId);
        return CustomResponse.onSuccess("리뷰 삭제 성공");
    }

    @GetMapping("/api/v1/reviews/my")
    public CustomResponse<ReviewResDTO.ReviewListDTO> getMyReview() {
        return CustomResponse.onSuccess(reviewQueryService.getMyReview());
    }
}
