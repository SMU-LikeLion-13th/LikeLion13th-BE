package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.convert.CommentConvert;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentDetailResDTO getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없다."));

        return CommentConvert.toCommentDetailResponse(comment);
    }

    @Override
    public CommentResDTO.CommentListResDTO getComments() {
        List<CommentResDTO.CommentDetailResDTO> comments = commentRepository.findAll().stream()
                .map(CommentConvert::toCommentDetailResponse).toList();
        return CommentResDTO.CommentListResDTO.builder().commentList(comments).build();
    }
}
