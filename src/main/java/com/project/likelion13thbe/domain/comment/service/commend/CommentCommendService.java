package com.project.likelion13thbe.domain.comment.service.commend;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentCommendService {
    CommentResDTO.CommentCreateResponseDTO createComment(CommentReqDTO.CommentCreateRequestDTO commentCreateRequestDTO);

    CommentResDTO.CommentPreviewResDTO updateComment(Long commentId, CommentReqDTO.CommentUpdateReqDTO dto);
}
