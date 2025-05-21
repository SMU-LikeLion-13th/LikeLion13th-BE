package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentCommandService {
    CommentResDTO.CommentCreateResDTO createComment(String email, Long reviewId, CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO);

    void updateComment(String email, Long commentId, CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO);

    void deleteComment(String email, Long commentId);
}
