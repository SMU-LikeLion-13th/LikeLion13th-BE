package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


public class CommentResDTO {

    //댓글 생성
    @Builder
    public record CommentCreateResponseDTO(
            Long commentId,
            LocalDateTime createdAt
    ) {
    }

    //댓글 단일 조회
    @Builder
    public record CommentResponseDTO(
            Long commentId,
            String content,
            String name,
            LocalDateTime createdAt,
            Integer likeCount
    ) {
    }

    @Builder
    public record CommentListResponseDTO(
            List<CommentResponseDTO> commentList
    ) {
    }



}