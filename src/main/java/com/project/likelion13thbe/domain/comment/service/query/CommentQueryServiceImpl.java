package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentResponseDTO getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없습니다."));

        return CommentConverter.toCommentResponseDTO(comment);
    }

    @Override
    public CommentResDTO.CommentListResponseDTO getComments() {
        List<CommentResDTO.CommentResponseDTO> comments = commentRepository.findAll().stream()
                .map(CommentConverter::toCommentResponseDTO).toList();
        return CommentResDTO.CommentListResponseDTO.builder().commentList(comments).build();
    }
}
