package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Commit", description = "댓글 관련 API")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(description = "댓글 목록 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/products/{productId}/reviews/{reviewId}/commits")
    public ResponseEntity<CommentResDTO.CommentListResDTO> getCommits(@PathVariable Long productId, @PathVariable Long reviewId) {
        return ResponseEntity.ok(commentQueryService.getCommentList(productId, reviewId));
    }

    @Operation(description = "댓글 작성")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @PostMapping("/api/v1/products/{productId}/reviews/{reviewId}/commits")
    public ResponseEntity<CommentResDTO.CommentCreateResDTO> createCommit(
            @PathVariable Long productId,
            @PathVariable Long reviewId,
            @RequestBody CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentCommandService.createComment(productId, reviewId, commentCreateReqDTO));
    }

    @Operation(description = "댓글 수정")
    @Parameter(name = "commitId", description = "commit PK", example = "1")
    @PatchMapping("/api/v1/commits/{commitId}")
    public ResponseEntity<CommentResDTO.CommentPreviewResDTO> updateCommit(
            @PathVariable Long commitId,
            @RequestBody CommentReqDTO.CommentUpdateReqDTO UpdateCommitDTO
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

