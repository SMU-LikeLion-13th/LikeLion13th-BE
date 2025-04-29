package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.review.dto.response.CommentResDTO;
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
@Tag(name = "댓글 관련", description = "댓글 관련 API")
public class CommentController {

    //댓글 목록 조회
    @Operation(summary = "댓글 목록 조회 API", description = "댓글 목록 조회 API입니다.")
    @GetMapping("/api/v1/users/{userId}/comments")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디")
    })
    public CommentResDTO.CommentResponseDTO getComment(@PathVariable long userId) { return null; }
    //댓글 작성
    @Operation(summary = "댓금 작성 API", description = "댓글 작성 API입니다.")
    @PostMapping("/api/v1/users/{userId}/comments/{commentId}")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디"),
            @Parameter(name = "commentId", description = "댓글 아이디")
    })
    public CommentResDTO.CommentResponseDTO postComment(@PathVariable long userId, long commentId) { return null; }
    //댓글 좋아요
    @Operation(summary = "댓글 좋아요 API", description = "댓글 좋아요 API입니다.")
    @PostMapping("/api/v1/users/{userId}/comments/{commentId}/likes")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디"),
            @Parameter(name = "commentId", description = "댓글 아이디")
    })
    public CommentResDTO.CommentResponseDTO postCommentLike(@PathVariable long userId, long commentId) { return null; }
    //댓글 수정
    @Operation(summary = "댓글 수정 API", description = "댓글 수정 API입니다.")
    @PatchMapping("/api/v1/users/{userId}/comments/{commentId}")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디"),
            @Parameter(name = "commentId", description = "댓글 아이디")
    })
    public CommentResDTO.CommentResponseDTO patchComment(@PathVariable long userId, long commentId) { return null; }
    //댓글 삭제
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제 API입니다.")
    @DeleteMapping("/api/v1/users/{userId}/comments/{commentId}")
    @Parameters({
            @Parameter(name = "userId", description = "유저 아이디"),
            @Parameter(name = "commentId", description = "댓글 아이디")
    })
    public CommentResDTO.CommentResponseDTO deleteComment(@PathVariable long userId, long commentId) { return null;}


}
