package com.project.likelion13thbe.domain.comment.convert;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConvert {
    public static Comment toComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Member member, Review review) {
        return Comment.builder()
                .description(commentCreateReqDTO.description())
                .member(member)
                .review(review)
                .build();
    }

    public static CommentResDTO.CommentCreateResDTO toCommentResponse(Comment comment) {
        return CommentResDTO.CommentCreateResDTO.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResDTO.CommentDetailResDTO toCommentDetailResponse(Comment comment) {
        return CommentResDTO.CommentDetailResDTO.builder()
                .commentId(comment.getId())
                .description(comment.getDescription())
                .createdAt(comment.getCreatedAt())
                .likes(comment.getLikes())
                .name(comment.getMember().getName())
                .build();
    }
}
