package com.project.likelion13thbe.domain.review.controller;

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

@RestController
@Tag(name="Review", description = "리뷰 관련 API")
public class ReviewController {
    @Operation(description = "리뷰 단건 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "Ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResDTO.ReviewDTO.class)
                    )
            )
    })
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/reviews/{reviewId}")
    public ResponseEntity<ReviewResDTO.ReviewDTO> getReview(@PathVariable Long reviewId) {
        return ResponseEntity.ok(null);
    }
}
