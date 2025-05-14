package com.project.likelion13thbe.domain.comment.service.command;


import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentCommandService {
    CommentResDTO.CommentCreateResDTO createComment(Long productId, Long reviewId, CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO);

    CommentResDTO.CommentPreviewResDTO updateComment(Long commentId,CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO);

    void deleteComment(Long commentId);
}
