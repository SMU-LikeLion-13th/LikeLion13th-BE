package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Comment", description = "댓글 관련 API")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(description = "댓글 목록 조회")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @GetMapping("/api/v1/products/{productId}/reviews/{reviewId}/comments")
    public CustomResponse<CommentResDTO.CommentListResDTO> getComments(
            @PathVariable("productId")  Long productId,
            @PathVariable("reviewId")  Long reviewId) {
        return CustomResponse.onSuccess(commentQueryService.getCommentList(productId, reviewId));
    }

    @Operation(description = "댓글 작성")
    @Parameter(name = "productId", description = "product PK", example = "1")
    @Parameter(name = "reviewId", description = "review PK", example = "1")
    @PostMapping("/api/v1/products/{productId}/reviews/{reviewId}/comments")
    public CustomResponse<CommentResDTO.CommentCreateResDTO> createComment(
            @PathVariable("productId")  Long productId,
            @PathVariable("reviewId") Long reviewId,
            @RequestBody @Valid CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO
    ) {
        return CustomResponse.onSuccess(commentCommandService.createComment(productId, reviewId, commentCreateReqDTO));
    }

    @Operation(description = "댓글 수정")
    @Parameter(name = "commentId", description = "comment PK", example = "1")
    @PatchMapping("/api/v1/comments/{commentId}")
    public CustomResponse<CommentResDTO.CommentPreviewResDTO> updateCommit(
            @PathVariable("commentId") Long commentId,
            @RequestBody @Valid CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO
    ) {
        return CustomResponse.onSuccess(commentCommandService.updateComment(commentId, commentUpdateReqDTO));
    }

    @Operation(description = "댓글 삭제")
    @Parameter(name = "commentId", description = "comment PK", example = "1")
    @DeleteMapping("/api/v1/comments/{commentId}")
    public CustomResponse<String> deleteCommit(@PathVariable("commentId") Long commentId) {
        commentCommandService.deleteComment(commentId);
        return CustomResponse.onSuccess("댓글 삭제 성공");
    }
}

