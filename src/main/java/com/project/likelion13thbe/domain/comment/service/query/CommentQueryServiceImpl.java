package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryServiceImpl implements CommentQueryService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDTO.CommentDetailResponseDTO getComment(Long commentId) {
        Comment comment = commentRepository.findByIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        return CommentConverter.toCommentDetailResponseDTO(comment);
    }

    @Override
    public CommentResponseDTO.CommentListResponseDTO getComments() {
        List<CommentResponseDTO.CommentDetailResponseDTO> comments = commentRepository.findAll().stream()
                .map(CommentConverter::toCommentDetailResponseDTO).toList();
        return CommentResponseDTO.CommentListResponseDTO.builder().commentList(comments).build();
    }
}
