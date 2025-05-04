package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {
    CommentResDTO.CommentListResDTO getCommentList(Long productId, Long reviewId);
}
