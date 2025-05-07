package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentQueryService {
    CommentResDTO.CommentResponseDTO getComment(Long commentId);

    CommentResDTO.CommentListResponseDTO getComments();
}
