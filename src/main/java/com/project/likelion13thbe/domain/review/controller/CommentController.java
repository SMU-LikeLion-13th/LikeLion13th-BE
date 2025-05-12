package com.project.likelion13thbe.domain.review.controller;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import com.project.likelion13thbe.domain.review.service.command.ReviewCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "댓글 관련", description = "댓글 관련 API")
public class CommentController {

    private final ReviewCommandService reviewCommandService;

    //댓글 목록 조회
    @Operation(summary = "댓글 목록 조회 API", description = "댓글 목록 조회 API입니다.")
    @GetMapping("/users/comments")
    public CommentResDTO.CommentResponseDTO getComment() { return null; }

    //댓글 작성
    @Operation(summary = "댓글 작성 API", description = "댓글 작성 API입니다.")
    @PostMapping("/users/comments")
    public ResponseEntity<ReviewResDTO.ReviewCreateResDTO> createComment(
            @RequestBody ReviewReqDTO.ReviewCreateReqDTO reviewCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reviewCommandService.createReview(reviewCreateReqDTO));
    }

    //댓글 좋아요
    @Operation(summary = "댓글 좋아요 API", description = "댓글 좋아요 API입니다.")
    @PostMapping("/users/comments/likes")
    public CommentResDTO.CommentResponseDTO postCommentLike() { return null; }

    //댓글 수정
    @Operation(summary = "댓글 수정 API", description = "댓글 수정 API입니다.")
    @PatchMapping("/users/comments")
    public CommentResDTO.CommentResponseDTO patchComment() { return null; }

    //댓글 삭제
    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제 API입니다.")
    @DeleteMapping("/users/comments")
    public CommentResDTO.CommentResponseDTO deleteComment() { return null;}


}
