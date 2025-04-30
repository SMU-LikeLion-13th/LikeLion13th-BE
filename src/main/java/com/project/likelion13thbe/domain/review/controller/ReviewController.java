package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Review",description = "리뷰 API")
@RestController
public class ReviewController {

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
        return null;
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
    @GetMapping("/products/{productid}/reviews")
    public ReviewResDTO.ReviewListResDTO getReviews(@PathVariable long productId) {
        return null;
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
    @PostMapping ("/products/{productid}/reviews")
    public ReviewResDTO.ReviewListResDTO postReviews(@PathVariable long productId,@RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return null;
    }

    @Operation(description = "리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> editReview(@PathVariable Long reviewId, @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return null;
    }

    @Operation(description = "리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "삭제",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        return null;
    }


}
