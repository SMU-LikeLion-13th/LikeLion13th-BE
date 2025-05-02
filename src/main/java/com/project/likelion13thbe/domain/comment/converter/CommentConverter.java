package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Member member, Review review) {
        return Comment.builder()
                .content(commentCreateReqDTO.content())
                .review(review)
                .member(member)
                .build();
    }

    public static CommentResDTO.CommentCreateResDTO toCommentCreateResDTO(Comment comment) {
        return CommentResDTO.CommentCreateResDTO.builder()
                .commentId(comment.getCommentId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
