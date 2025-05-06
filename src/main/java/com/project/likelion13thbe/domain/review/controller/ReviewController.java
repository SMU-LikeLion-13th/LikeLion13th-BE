package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import com.project.likelion13thbe.domain.review.service.query.ReviewQueryService;
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
@RequestMapping("/review")
@Tag(name = "리뷰 관련", description = "리뷰 관련 API")
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;


    //내 리뷰 조회
    @Operation(summary = "내 리뷰 조회 API", description = "내 리뷰 조회 API입니다.")
    @GetMapping("/api/v1/reviews")
    public ReviewResDTO.ReviewResponseDTO getReview() {return null;}

    //리뷰 목록 조회
    @Operation(summary = "리뷰 목록 조회 API", description = "리뷰 목록 조회 API입니다.")
    @GetMapping("/api/vi/reviews/products/offset")
    public ResponseEntity<ReviewResDTO.ReviewOffsetResDTO> getReviewOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(reviewQueryService.getReviewOffset(offset, size));
    }

    //리뷰 내용 수정
    @Operation(summary = "리뷰 내용 수정 API", description = "리뷰 내용 수정 API입니다.")
    @PatchMapping("/api/v1/reviews/{reviewId}")
    @Parameters({
            @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    })
    public ReviewResDTO.ReviewResponseDTO patchReview(@PathVariable long reviewId)  {
        return null;
    }

    //리뷰 내용 추가
    @Operation(summary = "리뷰 내용 추가 API", description = "리뷰 내용 수정 API입니다.")
    @PostMapping("/api/v1/products/reviews")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateReqDTO));
    }

    //리뷰 내용 삭제
    @Operation(summary = "리뷰 내용 삭제 API", description = "리뷰 내용 수정 API입니다.")
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
    public ResponseEntity<ReviewResDTO.ReviewPreviewResDTO> getDetailReview(@PathVariable long reviewId) {
        return ResponseEntity.ok(reviewQueryService.getReview());
    }


}
