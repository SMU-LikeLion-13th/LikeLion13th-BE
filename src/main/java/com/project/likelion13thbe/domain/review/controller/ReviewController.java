package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
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
@Tag(name = "리뷰 관련", description = "리뷰 관련 API")
public class ReviewController {

    //내 리뷰 조회
    @Operation(summary = "내 리뷰 조회 API", description = "내 리뷰 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @GetMapping("/api/v1/reviews/{reviewId}")
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO getReview(@PathVariable long reviewId) {return null;}

    //리뷰 목록 조회
    @Operation(summary = "리뷰 목록 조회 API", description = "리뷰 목록 조회 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @GetMapping("/api/vi/reviews/products/{productId}")
    @Parameters({
            @Parameter(name = "productId", description = "상품 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO getReviewList(@PathVariable long productId) {
        return null;
    }

    //리뷰 내용 수정
    @Operation(summary = "리뷰 내용 수정 API", description = "리뷰 내용 수정 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "Ok, 성공",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = ReviewResDTO.ReviewResponseDTO.class))
            )
    })
    @PatchMapping("/api/v1/reviews/{reviewId}")
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO patchReview(@PathVariable long reviewId)  {
        return null;
    }

    //리뷰 내용 추가
    @Operation(summary = "리뷰 내용 추가 API", description = "리뷰 내용 수정 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @PostMapping("/api/v1/products/{productId}/reviews")
    public ReviewResDTO.ReviewResponseDTO postReview(@PathVariable long productId) {
        return null;
    }

    //리뷰 내용 삭제
    @Operation(summary = "리뷰 내용 삭제 API", description = "리뷰 내용 수정 API입니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "COMMON200", description = "OK, 성공")
    })
    @DeleteMapping("/api/v1/reviews/{reviewId}")
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO deleteReview(@PathVariable long reviewId) {
        return null;
    }

    //리뷰 상세 조회
    @Operation(summary = "리뷰 상세 조회 API", description = "리뷰 상세 조회 API입니다.")
    @GetMapping("/api/v1/users/reviews/{reviewId}")
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO getDetailReview(@PathVariable long reviewId, long userId) {return null;}


}
