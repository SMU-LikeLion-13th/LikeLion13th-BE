package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO, Member member, Review review) {
        return Comment.builder()
                .content(commentCreateRequestDTO.content())
                .member(member)
                .review(review)
                .build();
    }

    public static CommentResponseDTO.CommentCreateResponseDTO toCommentResponseDTO(Comment comment) {
        return CommentResponseDTO.CommentCreateResponseDTO.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
