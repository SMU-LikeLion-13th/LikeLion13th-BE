package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
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
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!review.getProduct().getId().equals(productId)) {
            throw new ReviewException(ReviewErrorCode.REVIEW_PRODUCT_MISMATCH);
        }

        List<Comment> comments = commentRepository.findAllByReviewId(reviewId);

        if (comments.isEmpty())
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND);

        return CommentConverter.toCommentPreviewResponseDTOList(comments);
    }
}
