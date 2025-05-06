package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.CommentCreateRequestDTO commentCreateResponseDTO) {
        return Comment.builder()
                .content(commentCreateResponseDTO.content())
                .nickname(commentCreateResponseDTO.nickname())
                .build();
    }

    public static CommentResponseDTO.CommentCreateResponseDTO toCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.CommentCreateResponseDTO.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .nickname(comment.getNickname())
                .build();
    }

    public static CommentResponseDTO.CommentPreviewResDTO toCommentPreviewResDTO(Comment comment) {
        return CommentResponseDTO.CommentPreviewResDTO.builder()
                .commentId(comment.getCommentId())
                .nickname(comment.getNickname())
                .content(comment.getContent())
                .build();
    }


    public static CommentResponseDTO.CommentResDTO toCommentResDTO(Comment comment) {
        return CommentResponseDTO.CommentResDTO.builder()
                .commentId(comment.getCommentId())
                .content(comment.getContent())
                .nickname(comment.getNickname())
                .build();
    }

    public static CommentResponseDTO.CommentListResponseDTO toCommentListResponseDTO(List<Comment> comments) {
        List<CommentResponseDTO.CommentResDTO> commentResDTOs = comments.stream()
                .map(CommentConverter::toCommentResDTO)
                .collect(Collectors.toList());

        return CommentResponseDTO.CommentListResponseDTO.builder()
                .comments(commentResDTOs)
                .build();
    }

}
