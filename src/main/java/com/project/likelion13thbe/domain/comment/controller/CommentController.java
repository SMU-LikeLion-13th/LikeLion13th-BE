package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("comment")
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 목록 조회")
    @GetMapping("reviews/{reviewId}/comments")
    public CustomResponse<CommentResDTO.CommentListResDTO> getComment(@PathVariable Long reviewId) {
        return CustomResponse.onSuccess(commentQueryService.getCommentList(reviewId));
    }

    @Operation(summary = "댓글 작성")
    @PostMapping("reviews/{reviewId}/comments")
    public CustomResponse<CommentResDTO.CommentCreateResDTO> createComment(
            @PathVariable Long reviewId,
            @RequestBody @Valid CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {
        return CustomResponse.onSuccess(HttpStatus.CREATED, commentCommandService.createComment(commentCreateReqDTO, reviewId));
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("comment/{commentId}")
    public CustomResponse<String> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO) {

        commentCommandService.updateComment(commentUpdateReqDTO, commentId);

        return CustomResponse.onSuccess("댓글 수정 완료");
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("comment/{commentId}")
    public CustomResponse<String> deleteComment(@PathVariable Long commentId) {

        commentCommandService.deleteComment(commentId);

        return CustomResponse.onSuccess(HttpStatus.NO_CONTENT, "댓글 삭제 완료");
    }
}
