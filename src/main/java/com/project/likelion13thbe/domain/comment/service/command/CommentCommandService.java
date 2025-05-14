package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;

public interface CommentCommandService {
    CommentResDTO.CommentCreateResDTO createComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO);
    void updateComment(Long commentId, CommentReqDTO.CommentUpdateDTO commentUpdateDTO);

    void deleteComment(Long commentId);
}
