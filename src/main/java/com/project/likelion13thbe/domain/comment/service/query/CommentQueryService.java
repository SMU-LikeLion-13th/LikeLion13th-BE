package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {
    CommentResDTO.CommentListResDTO getCommentList(Long reviewId);

    CommentResDTO.CommentCursorResDTO getCommentCursor(Long reviewId, Long cursor, Integer size);
}
