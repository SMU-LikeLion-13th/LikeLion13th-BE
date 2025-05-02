package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {
    public CommentResDTO.CommentListResDTO getCommentList(Long reviewId);
}
