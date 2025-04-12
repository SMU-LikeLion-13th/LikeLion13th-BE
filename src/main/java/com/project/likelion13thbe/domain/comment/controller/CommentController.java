package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
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
    @GetMapping("/api/v1/products/{productId}/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponseDTO.CommentListResponseDTO> getCommentList(
            @PathVariable Long productId, @PathVariable Long reviewId
    ) {
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
    @PostMapping("/api/v1/products/{productId}/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponseDTO.CommentCreateResponseDTO> createComment(
            @PathVariable Long productId, @PathVariable Long reviewId, @RequestBody CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO
    ) {
        return null;
    }
}
