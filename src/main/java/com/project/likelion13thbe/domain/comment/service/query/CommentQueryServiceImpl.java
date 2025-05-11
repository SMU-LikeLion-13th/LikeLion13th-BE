package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDTO.CommentPreviewResDTO getComment() {
        // DB에서 pk가 1인 Comment 조회
        Comment comment = commentRepository.findById(1L).get();

        // 응답 DTO로 변환 후 return
        return CommentConverter.toCommentPreviewResDTO(comment);
    }

    @Override
    public CommentResponseDTO.CommentListResponseDTO getCommentList() {
        List<Comment> comments = commentRepository.findAll();
        return CommentConverter.toCommentListResponseDTO(comments);
    }

    @Override
    public CommentResponseDTO.CommentOffsetResponseDTO getCommentOffset(Integer offset,Integer size) {
        Pageable pageable = PageRequest.of(offset-1, size);
        Page<Comment> Comments = commentRepository.findAllByOrderByCreatedAtDesc(pageable);

        return CommentConverter.toCommentOffsetResponseDTO(Comments);
    }
}
