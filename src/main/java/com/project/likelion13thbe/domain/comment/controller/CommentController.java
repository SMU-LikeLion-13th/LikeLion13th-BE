package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Comment",description = "댓글 API")
public class CommentController {
    @Operation(description = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentResDTO.CommentListResDTO.class))),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResDTO.CommentListResDTO> getCommentList(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(description = "댓글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CommentResDTO.CommentCreateResDTO.class))),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResDTO.CommentCreateResDTO> createComment(@PathVariable Long reviewId,@RequestBody CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO
    ) {
        return null;
    }

    @Operation(description = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> editComment(@PathVariable String commentId) {
        return null;
    }

    @Operation(description = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        return null;
    }
}
