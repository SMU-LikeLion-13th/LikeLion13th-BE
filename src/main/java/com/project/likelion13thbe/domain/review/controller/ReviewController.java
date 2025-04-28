package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Review", description = "리뷰 관련 API")
public class ReviewController {

    @Operation(summary = "리뷰 세부 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewDetailResDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewDetailResDTO> getReview(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "리뷰 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewListResDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/products/{productId}/reviews")
    public ResponseEntity<ReviewResDTO.ReviewListResDTO> getReviews(@PathVariable Long productId) {
        return null;
    }

}
