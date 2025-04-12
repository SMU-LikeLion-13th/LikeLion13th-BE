package com.project.likelion13thbe.domain.comment.dto.response;

import lombok.Builder;

import java.util.List;

public class CommentResponseDTO {

    @Builder
    public record CommentDetailResponseDTO(
            Long commentId,
            String content,
            String nickname,
            Integer likeCount
    ) {
    }

    @Builder
    public record CommentListResponseDTO(
            List<CommentDetailResponseDTO> commentList
    ) {
    }
}
