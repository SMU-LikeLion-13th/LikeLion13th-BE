package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "댓글 API")
@RequestMapping("/api/v1")
@RestController
public class CommentController {

    @Operation(summary = "댓글 조회")
    @GetMapping("/reviews/{reviewId}/comments")
    public CommentResDTO.CommentResponseDTO getComment(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "댓글 좋아요")
    @PostMapping("reviews/{reviewId}/comments/{commentId}/like")
    public CommentResDTO.CommentResponseDTO postCommentLike(@PathVariable Long reviewId, @PathVariable Long commentId) {
        return null;
    }


    @Operation(summary = "댓글 작성")
    @PostMapping("/users/{userId}/comments")
    public CommentResDTO.CommentResponseDTO postComment(@PathVariable long userId ,@RequestBody CommentReqDTO CommentReqDTO) {
        return null;
    }

    @Operation(summary = "댓글 수정")
    @PatchMapping("comments/{commentId}")
    public CommentResDTO.CommentResponseDTO patchComment(@PathVariable Long commentId) {
        return null;
    }// 단일 수정이니까 Id만 있으면 되나?

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/comments/{commentId}")
    public CommentResDTO.CommentResponseDTO deleteComment(@PathVariable Long commentId) {
        return null;
    }








}
