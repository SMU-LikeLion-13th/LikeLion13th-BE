package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;

public interface CommentCommandService {

    CommentResponseDTO.CommentCreateResponseDTO createComment(Long reviewId, CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO);
}
