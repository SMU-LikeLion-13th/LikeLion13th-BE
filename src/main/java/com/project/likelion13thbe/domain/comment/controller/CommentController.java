package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment", description = "댓글 관련 API")
@RequestMapping("/api/v1")
public class CommentController {

    private final CommentQueryService commentQueryService;
    private final CommentCommandService commentCommandService;

    @Operation(summary = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommentResponseDTO.CommentListResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/reviews/{reviewId}/comments")
    public CustomResponse<CommentResponseDTO.CommentListResponseDTO> getCommentList(@PathVariable Long reviewId) {
        return CustomResponse.onSuccess(commentQueryService.getComments());
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
    @PostMapping("/reviews/{reviewId}/comments")
    public CustomResponse<CommentResponseDTO.CommentCreateResponseDTO> createComment(@PathVariable Long reviewId,
                                                                                     @RequestBody CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO
    ) {
        return CustomResponse.onSuccess(HttpStatus.CREATED, commentCommandService.createComment(reviewId, commentCreateRequestDTO));
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
    @PatchMapping("/comments/{commentId}")
    public CustomResponse<String> editComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDTO.CommentUpdateRequestDTO commentUpdateRequestDTO) {
        commentCommandService.updateComment(commentId, commentUpdateRequestDTO);
        return CustomResponse.onSuccess("댓글 수정 완료");
    }

    @Operation(summary = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "NotFound",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/comments/{commentId}")
    public CustomResponse<?> deleteComment(@PathVariable Long commentId) {
        return null;
    }
}
