package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandServiceImpl;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryServiceImpl;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Comment",description = "댓글 API")
public class CommentController {
    private final CommentCommandServiceImpl commentCommandService;
    private final CommentQueryServiceImpl commentQueryService;
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
        return ResponseEntity.ok(commentQueryService.getComments());

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
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentCommandService.createComment(commentCreateReqDTO));

    }

    @Operation(description = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/comments/{commentId}")
    public CustomResponse<String> editComment(
            @PathVariable Long commentId,
            @RequestBody CommentReqDTO.CommentUpdateDTO commentUpdateDTO
    ) {
        commentCommandService.updateComment(commentId, commentUpdateDTO);
        return CustomResponse.onSuccess("댓글 수정 성공");
    }

    @Operation(description = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/comments/{commentId}")
    public CustomResponse<String> deleteComment(
            @PathVariable Long commentId) {
        commentCommandService.deleteComment(commentId);
        return CustomResponse.onSuccess("댓글 삭제 성공");
    }
}
