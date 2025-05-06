package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {

    @Operation(summary = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.CommentListResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/api/v1/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponseDTO.CommentListResponseDTO> getCommentList(@PathVariable Long reviewId) {
        return null;
    }

    @Operation(summary = "댓글 작성")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.CommentCreateResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponseDTO.CommentCreateResponseDTO> createComment(@PathVariable Long reviewId,
                                                                                     @RequestBody CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentCommandServiceImpl.createComment(commentCreateRequestDTO));
    }

    @Operation(summary = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/api/v1/comments/{commentId}")
    public ResponseEntity<?> editComment(@PathVariable String commentId) {
        return null;
    }

    @Operation(summary = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/api/v1/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return null;
    }
}
