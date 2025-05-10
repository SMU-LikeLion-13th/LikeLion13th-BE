package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;

public interface CommentQueryService {
    public CommentResponseDTO.CommentPreviewResDTO getComment();

    public CommentResponseDTO.CommentListResponseDTO getCommentList();
}
