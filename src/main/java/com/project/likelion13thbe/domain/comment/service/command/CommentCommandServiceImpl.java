package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDTO.CommentCreateResponseDTO createComment(CommentRequestDTO.CommentCreateRequestDTO requestDTO) {
        // DTO → Entity
        Comment comment = CommentConverter.toComment(requestDTO);

        // 저장
        commentRepository.save(comment);

        // Entity → ResponseDTO
        return CommentConverter.toCommentResponseDTO(comment);
    }

    @Override
    public void updateComment(Long commentId,CommentRequestDTO.CommentUpdateRequestDTO dto) {

        Comment comment = commentRepository.findByIdNotDeleted(commentId).orElseThrow(() -> new CommentException(CommentErrorCode.Comment_NOT_FOUND));

        comment.updateComment(dto.getContent());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByIdNotDeleted(commentId).orElseThrow(() -> new CommentException(CommentErrorCode.Comment_NOT_FOUND));

        comment.delete();
    }
}
