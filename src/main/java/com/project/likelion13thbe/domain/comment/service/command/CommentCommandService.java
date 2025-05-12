package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;

public interface CommentCommandService {
    public CommentResDTO.CommentCreateResDTO createComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Long reviewId);

    public void deleteComment(Long commentId);
}
