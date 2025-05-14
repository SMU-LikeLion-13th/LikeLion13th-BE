package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO.CommentPreviewResDTO;
import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(CommentReqDTO.CommentCreateRequestDTO commentCreateRequestDTO, Member member, Review review) {
        return Comment.builder()
                .content(commentCreateRequestDTO.content())
                .member(member)
                .review(review)
                .build();
    }

    public static CommentResDTO.CommentCreateResponseDTO toCommentCreateResponseDTO(Comment comment) {
        return CommentResDTO.CommentCreateResponseDTO.builder()
                .commentId(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentPreviewResDTO toPreviewDTO(Comment comment) {
        return CommentPreviewResDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .name(comment.getMember().getName())
                .likeCount(comment.getLikeCount())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResDTO.CommentCursorResDTO toCursorResDTO(Slice<Comment> comments) {
        List<CommentResDTO.CommentPreviewResDTO> commentList = comments.stream()
                .map(CommentConverter::toPreviewDTO)
                .toList();

        Long nextCursor = null;
        if (!comments.isEmpty() && comments.hasNext()) {
            nextCursor = comments.getContent().get(comments.getNumberOfElements() - 1).getId();
        }

        return CommentResDTO.CommentCursorResDTO.builder()
                .comments(commentList)
                .hasNext(comments.hasNext())
                .nextCursor(nextCursor)
                .build();
    }
}
