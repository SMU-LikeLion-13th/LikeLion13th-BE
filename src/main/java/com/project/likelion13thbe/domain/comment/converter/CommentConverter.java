package com.project.likelion13thbe.domain.comment.converter;

import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.entity.Review;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentConverter {

    public static Comment toComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Member member, Product product, Review review) {
        return Comment.builder()
                .content(commentCreateReqDTO.content())
                .member(member)
                .product(product)
                .review(review)
                .build();

    }
    public static CommentResDTO.CommentCreateResDTO toCommentResponseDTO(Comment comment) {
        return CommentResDTO.CommentCreateResDTO.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResDTO.CommentPreviewResDTO toCommentPreviewResponseDTO(Comment comment) {
        return CommentResDTO.CommentPreviewResDTO.builder()
                .id(comment.getId())
                .username(comment.getMember().getName())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public static CommentResDTO.CommentListResDTO toCommentPreviewResponseDTOList(List<Comment> comments) {
        return CommentResDTO.CommentListResDTO.builder()
                .comments(comments.stream()
                        .map(CommentConverter::toCommentPreviewResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
