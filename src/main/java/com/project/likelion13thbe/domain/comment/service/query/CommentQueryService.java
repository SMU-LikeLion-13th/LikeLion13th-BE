package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {

    CommentResDTO.CommentCursorResDTO getCommentsByReview(Long reviewId, Long cursor, int size);
}
