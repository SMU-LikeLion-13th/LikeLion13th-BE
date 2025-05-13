package com.project.likelion13thbe.domain.comment.service.command;

import com.project.likelion13thbe.domain.comment.convert.CommentConvert;
import com.project.likelion13thbe.domain.comment.dto.request.CommentReqDTO;
import com.project.likelion13thbe.domain.comment.dto.response.CommentResDTO;
import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.comment.exception.CommentErrorCode;
import com.project.likelion13thbe.domain.comment.exception.CommentException;
import com.project.likelion13thbe.domain.comment.repository.CommentRepository;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.entity.Review;
import com.project.likelion13thbe.domain.review.exception.ReviewErrorCode;
import com.project.likelion13thbe.domain.review.exception.ReviewException;
import com.project.likelion13thbe.domain.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResDTO.CommentCreateResDTO createComment(CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {
        Member member = memberRepository.findById(commentCreateReqDTO.memberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Review review = reviewRepository.findById(commentCreateReqDTO.reviewId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        Comment comment = CommentConvert.toComment(commentCreateReqDTO, member, review);

        commentRepository.save(comment);

        return CommentConvert.toCommentResponse(comment);
    }
    @Override
    public void updateComment(Long commentId, CommentReqDTO.CommentUpdateDTO commentUpdateDTO) {
        Comment comment=commentRepository.findByIdAndNotDeleted(commentId)
                .orElseThrow(()-> new CommentException(CommentErrorCode.COMMENT_ERROR_CODE));
        comment.updateComment(commentUpdateDTO.getDescription());
    }
    @Override
    public void deleteComment(Long commentId){
        Comment comment=commentRepository.findByIdAndNotDeleted(commentId)
                .orElseThrow(()-> new CommentException(CommentErrorCode.COMMENT_ERROR_CODE));
        comment.delete();
    }
}
