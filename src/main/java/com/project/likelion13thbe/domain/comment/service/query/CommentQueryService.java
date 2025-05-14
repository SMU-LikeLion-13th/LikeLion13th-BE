package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;

public interface CommentQueryService {

    CommentResponseDTO.CommentDetailResponseDTO getComment(Long commentId);

    CommentResponseDTO.CommentListResponseDTO getComments();

    CommentResponseDTO.CommentCursorResponseDTO getCommentCursor(Long reviewId, Long cursor, Integer size);

}
