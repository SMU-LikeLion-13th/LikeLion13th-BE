package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;

public interface CommentQueryService {
    CommentResponseDTO.CommentPreviewResDTO getComment();

    CommentResponseDTO.CommentListResponseDTO getCommentList();

    CommentResponseDTO.CommentOffsetResponseDTO getCommentOffset(Integer offset, Integer size);
}
