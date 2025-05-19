package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public CustomResponse<ReviewResDTO.ReviewCreateResDTO> createReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long productId,
            @RequestBody @Valid ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return CustomResponse.onSuccess(HttpStatus.CREATED, reviewCommandService.createReview(userDetails.getUsername(), reviewCreateReqDTO, productId));
    }

    @Operation(summary = "리뷰 수정")
    @PatchMapping("reviews/{reviewId}")
    public CustomResponse<String> updateReview(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long reviewId,
            @RequestBody @Valid ReviewReqDTO.ReviewUpdateReqDTO reviewUpdateReqDTO) {

        reviewCommandService.updateReview(userDetails.getUsername(), reviewUpdateReqDTO, reviewId);

        return CustomResponse.onSuccess("리뷰 수정 완료");
    }

    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("reviews/{reviewId}")
    public CustomResponse<String> deleteReview(@PathVariable Long reviewId) {

        reviewCommandService.deleteReview(reviewId);

        return CustomResponse.onSuccess(HttpStatus.NO_CONTENT, "리뷰 삭제 완료");
    }

    @Operation(summary = "내 리뷰 조회")
    @GetMapping("/reviews/my")
    public CustomResponse<ReviewResDTO.ReviewListResDTO> getMyReviews() {
        return CustomResponse.onSuccess(reviewQueryService.getMyReviewList());
    }

    @Operation(summary = "리뷰 목록 조회 (커서 방식)")
    @GetMapping("products/{productId}/reviews-cursor/")
    public CustomResponse<ReviewResDTO.ReviewCursorResDTO> getReviewCursor(
            @PathVariable Long productId,
            @RequestParam Long cursor,
            @RequestParam Integer size
    ) {
        return CustomResponse.onSuccess(reviewQueryService.getReviewCursor(productId, cursor, size));
    }
}
