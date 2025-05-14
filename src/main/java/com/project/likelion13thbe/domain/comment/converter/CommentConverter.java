package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

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

    public static CommentResponseDTO.CommentDetailResponseDTO toCommentDetailResponseDTO(Comment comment) {
        return CommentResponseDTO.CommentDetailResponseDTO.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .likeCount(comment.getLikeCount())
                .nickname(comment.getMember().getName())
                .build();
    }

    public static CommentResponseDTO.CommentCursorResponseDTO toCommentCursorResponseDTO(Slice<Comment> comments) {
        List<CommentResponseDTO.CommentDetailResponseDTO> commentList = comments.stream()
                .map(CommentConverter::toCommentDetailResponseDTO)
                .toList();

        Long nextCursor = null;
        if (!comments.isEmpty() && comments.hasNext()) {
            nextCursor = comments.getContent().get(comments.getNumberOfElements() - 1).getId();
        }

        return CommentResponseDTO.CommentCursorResponseDTO.builder()
                .comments(commentList)
                .hasNext(comments.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
