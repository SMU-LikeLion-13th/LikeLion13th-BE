package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@Tag(name="Review", description="Review 관련 API입니다.")
public class ReviewController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createReview(
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateReqDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateReview(
            @PathVariable Long id,
            @RequestBody ReviewReqDTO.ReviewUpdateReqDTO dto
    ) {
        reviewCommandService.updateReview(id, dto);
        return ResponseEntity.ok("Review Updated Successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable Long id
    ) {
        reviewCommandService.deleteReview(id);
        return ResponseEntity.ok("Review Deleted Successfully.");
    }
}
