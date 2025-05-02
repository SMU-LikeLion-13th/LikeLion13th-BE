package com.project.likelion13thbe.domain.comment.controller;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.service.command.CommentCommandService;
import com.project.likelion13thbe.domain.comment.service.query.CommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("comment")
@Tag(name = "Comment", description = "댓글 관련 API")
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(summary = "댓글 목록 조회")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "OK",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = CommentResDTO.CommentListResDTO.class))),
//            @ApiResponse(responseCode = "404", description = "Not Found",
//                    content = @Content(mediaType = "application/json"))
//    })
    @GetMapping("reviews/{reviewId}/comments")
    public ResponseEntity<CommentResDTO.CommentListResDTO> getComment(@PathVariable Long reviewId) {
        return ResponseEntity.ok(commentQueryService.getCommentList(reviewId));
    }

    @Operation(summary = "댓글 작성")
//    @ApiResponses({
//            @ApiResponse(responseCode = "201", description = "Created",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "400", description = "Bad Request",
//                    content = @Content(mediaType = "application/json")),
//            @ApiResponse(responseCode = "404", description = "Not Found",
//                    content = @Content(mediaType = "application/json"))
//    })
    @PostMapping("reviews/{reviewId}/comments")
    public ResponseEntity<CommentResDTO.CommentCreateResDTO> createComment(
            @PathVariable Long reviewId, @RequestBody CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentCommandService.createComment(commentCreateReqDTO, reviewId));
    }

    @Operation(summary = "댓글 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @PatchMapping("/api/v1/comment/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO) {
        return null;
    }

    @Operation(summary = "댓글 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("api/v1/comment/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        return null;
    }
}
