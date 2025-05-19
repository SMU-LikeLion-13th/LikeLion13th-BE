package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;

public interface CommentCommandService {

    CommentResponseDTO.CommentCreateResponseDTO createComment(Long reviewId, CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO, Member member);

    void updateComment(Long commentId, CommentRequestDTO.CommentUpdateRequestDTO commentUpdateRequestDTO);

    void deleteComment(Long commentId);
}
