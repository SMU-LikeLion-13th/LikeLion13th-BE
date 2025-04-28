package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommitReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommitResDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Commit", description = "댓글 관련 API")
public class CommitController {
    @Operation(description = "댓글 목록 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/products/{productId}/reviews/{reviewId}/commits")
    public ResponseEntity<CommitResDTO.CommentListResDTO> getCommits(@PathVariable Long productId, @PathVariable Long reviewId) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "댓글 작성")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @PostMapping("/api/v1/products/{productId}/reviews/{reviewId}/commits")
    public ResponseEntity<CommitResDTO.CommentResDTO> createCommit(
            @PathVariable Long productId,
            @PathVariable Long reviewId,
            @RequestBody CommitReqDTO.CreateCommitDTO createCommitDTO
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "댓글 수정")
    @Parameter(name = "commitId", description = "commit PK", example = "1")
    @PatchMapping("/api/v1/commits/{commitId}")
    public ResponseEntity<CommitResDTO.CommentResDTO> updateCommit(
            @PathVariable Long commitId,
            @RequestBody CommitReqDTO.UpdateCommitDTO UpdateCommitDTO
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "댓글 삭제")
    @Parameter(name = "commitId", description = "commit PK", example = "1")
    @DeleteMapping("/api/v1/commits/{commitId}")
    public ResponseEntity<Void> deleteCommit() {
        return ResponseEntity.ok(null);
    }
}

