package com.project.likelion13thbe.domain.review.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.review.entity.Comment;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(
            CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Member member, Review review) {
        return Comment.builder()
                .date(commentCreateReqDTO.date())
                .content(commentCreateReqDTO.content())
                .member(member)
                .review(review)
                .build();
    }

    public static CommentResDTO.CommentCreateResDTO toCommentCreateResDTO(Comment comment) {
        return new CommentResDTO.CommentCreateResDTO(
                comment.getCommentId(),
                comment.getCreatedAt()
        );
    }
}
