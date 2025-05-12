package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentRequestDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResponseDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResponseDTO.CommentCreateResponseDTO createComment(Long reviewId, CommentRequestDTO.CommentCreateRequestDTO commentCreateRequestDTO) {
        Member member = memberRepository.findById(commentCreateRequestDTO.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        Comment comment = CommentConverter.toComment(commentCreateRequestDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentResponseDTO(comment);
    }

    @Override
    public void updateComment(Long commentId, CommentRequestDTO.CommentUpdateRequestDTO commentUpdateRequestDTO) {
        Comment comment = commentRepository.findByIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.update(commentUpdateRequestDTO.content());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.delete();
    }
}
