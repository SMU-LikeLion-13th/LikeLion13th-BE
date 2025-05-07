package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {
    CommentResDTO.CommentDetailResDTO getComment(Long commentId);

    CommentResDTO.CommentListResDTO getComments();
}
