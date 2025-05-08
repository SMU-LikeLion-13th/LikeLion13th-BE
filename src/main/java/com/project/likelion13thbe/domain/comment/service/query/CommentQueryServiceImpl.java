package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    public CommentQueryServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponseDTO.CommentPreviewResDTO getComment() {
        // DB에서 pk가 1인 Comment 조회
        Comment comment = commentRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return CommentConverter.toCommentPreviewResDTO(comment);
    }

    public CommentResponseDTO.CommentListResponseDTO getCommentList() {
        List<Comment> comments = commentRepository.findAll();
        return CommentConverter.toCommentListResponseDTO(comments);
    }
}
