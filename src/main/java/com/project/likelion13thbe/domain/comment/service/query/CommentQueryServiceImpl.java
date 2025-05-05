package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentQueryServiceImpl implements CommentQueryService {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentListResDTO getCommentList(Long productId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();

        if (!review.getProduct().getId().equals(productId)) {
            throw new IllegalArgumentException("리뷰가 해당 상품에 속하지 않습니다.");
        }
        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);

        return CommentConverter.toCommentPreviewResponseDTOList(comments);
    }
}
