package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandServiceImpl;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryServiceImpl;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
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

@Tag(name="Review",description = "리뷰 API")
@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewCommandServiceImpl reviewCommandServiceImpl;
    private final ReviewQueryServiceImpl reviewQueryServiceImpl;
    private final ReviewCommandService reviewCommandService;

    @Operation(description = "리뷰 세부 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResDTO.ReviewDetailResDTO.class))),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewDetailResDTO> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewQueryServiceImpl.getReview(reviewId));
    }

    @Operation(description = "리뷰 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResDTO.ReviewListResDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewListResDTO>  getReviews(@PathVariable Long productId) {
        return ResponseEntity.ok(reviewQueryServiceImpl.getReviews());
    }

    @Operation(description = "리뷰 생성")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResDTO.ReviewListResDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")),
    })
    @Parameter(name="productId",description = "product PK",example = "1")
    @PostMapping ("/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> postReviews(
            @PathVariable long productId,
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandServiceImpl.createReview(reviewCreateReqDTO));
    }

    @Operation(description = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/reviews/{reviewId}")
    public CustomResponse<String> editReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewReqDTO.ReviewUpdateDTO reviewUpdateDTO) {
        reviewCommandService.updateReview(reviewId, reviewUpdateDTO);
        return CustomResponse.onSuccess("리뷰 수정 성공");
    }

    @Operation(description = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "삭제",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public CustomResponse<String> deleteReview(@PathVariable Long reviewId) {
        reviewCommandService.deleteReview(reviewId);
        return CustomResponse.onSuccess("리뷰 삭제 성공");
    }
}
