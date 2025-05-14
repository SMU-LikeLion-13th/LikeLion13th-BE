package com.project.likelion13thbe.domain.comment.service.query;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentQueryServiceImpl implements CommentQueryService {
    private final CommentRepository commentRepository;

    @Override
    public CommentResDTO.CommentCursorResDTO getCommentsByReview(Long reviewId, Long cursor, int size) {

        Pageable pageable = PageRequest.of(0, size);

        if (cursor == 0) {
            cursor = Long.MAX_VALUE; // 커서 없을 경우 최신 댓글부터
        }

        Slice<Comment> comments = commentRepository.findByReviewIdAndIdLessThanOrderByIdDesc(reviewId, cursor, pageable);

        if (comments.isEmpty()) {
            throw new CommentException(CommentErrorCode.COMMENT_NOT_FOUND);
        }

        return CommentConverter.toCursorResDTO(comments);
    }
}
