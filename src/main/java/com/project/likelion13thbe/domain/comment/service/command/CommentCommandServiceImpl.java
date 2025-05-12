package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.converter.CommentConverter;
import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
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
    public CommentResDTO.CommentCreateResDTO createComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO, Long reviewId) {
        Member member = memberRepository.findByMemberIdAndNotDeleted(commentCreateReqDTO.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Review review = reviewRepository.findByReviewIdAndNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        Comment comment = CommentConverter.toComment(commentCreateReqDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentCreateResDTO(comment);
    }

    @Override
    public void updateComment(CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO, Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.updateComment(commentUpdateReqDTO.content());
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.delete();
    }
}
