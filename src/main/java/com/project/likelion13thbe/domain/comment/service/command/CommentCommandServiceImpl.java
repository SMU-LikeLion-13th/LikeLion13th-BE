package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;

    public CommentResponseDTO.CommentCreateResponseDTO createComment(CommentRequestDTO.CommentCreateRequestDTO requestDTO) {
        // DTO → Entity
        Comment comment = CommentConverter.toComment(requestDTO);

        // 저장
        commentRepository.save(comment);

        // Entity → ResponseDTO
        return CommentConverter.toCommentResponseDTO(comment);
    }
}
