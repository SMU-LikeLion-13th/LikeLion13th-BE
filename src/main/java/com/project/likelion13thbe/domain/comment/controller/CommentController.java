package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {

    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 목록 조회 API", description = "댓글 목록 조회")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "댓글 목록 조회 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.CommentListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "댓글 목록 조회 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    @GetMapping("/reviews/{reviewId}/comments")
    public CustomResponse<CommentResponseDTO.CommentOffsetResponseDTO> getComments(
            @PathVariable Long reviewId,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return CustomResponse.onSuccess(commentQueryService.getCommentOffset(offset,size));
    }

    @Operation(summary = "댓글 수정 API", description = "댓글 수정")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "댓글 수정 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.CommentListResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "댓글 수정 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )

    })
    @PatchMapping("/comments/{commentId}")
    public CustomResponse<String> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDTO.CommentUpdateRequestDTO requestDTO
    ) {
        commentCommandService.updateComment(commentId, requestDTO);
        // 댓글 수정 로직
        return CustomResponse.onSuccess("댓글 수정 성공");
    }

    @Operation(summary = "댓글 생성 API", description = "댓글 생성")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "댓글 생성 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.CommentCreateResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "댓글 생성 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameter(name = "reviewId", description = "리뷰 아이디", example = "1")
    @PostMapping("/reviews/{reviewId}/comments")
    public ResponseEntity<CommentResponseDTO.CommentCreateResponseDTO> postComment(
            @RequestBody CommentRequestDTO.CommentCreateRequestDTO requestDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentCommandService.createComment(requestDTO));
    }

    @Operation(summary = "댓글 삭제 API", description = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "COMMON200", description = "댓글 삭제 성공입니다.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CommentResponseDTO.CommentCreateResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "댓글 삭제 실패입니다.",
                    content = @Content(mediaType = "application/json")
            )
    })
    @Parameters({
            @Parameter(name = "commentId", description = "댓글 아이디", example = "1")
    })
    @DeleteMapping("/comments/{commentId}")
    public CustomResponse<String> deleteComment(@PathVariable Long commentId) {
        commentCommandService.deleteComment(commentId);
        return CustomResponse.onSuccess("댓글 삭제 성공");
    }

}
