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
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;


    @Override
    public CommentResDTO.CommentCreateResDTO createComment(String email, Long reviewId, CommentReqDTO.CommentCreateReqDTO commentCreateReqDTO) {
        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Review review = reviewRepository.findByReviewIdAndNotDeleted(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        Comment comment = CommentConverter.toComment(commentCreateReqDTO, member, review);

        commentRepository.save(comment);

        return CommentConverter.toCommentCreateResDTO(comment);
    }

    @Override
    public void updateComment(String email, Long commentId, CommentReqDTO.CommentUpdateReqDTO commentUpdateReqDTO) {
        Comment comment = commentRepository.findByCommentIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));
        if (comment.getMember().getEmail().equals(email)) {
            comment.updateComment(commentUpdateReqDTO.content());
            return;
        }
        throw new CommentException(CommentErrorCode.COMMENT_ACCESS_DENIED);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findByCommentIdAndNotDeleted(commentId)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.delete();
    }

    @Scheduled(cron = "0 0 6 * * *")
    @Transactional
    public void cleanupDeletedComment() {
        log.info("삭제된 댓글을 제거하는 스케쥴 시작");

        // 일주일 전 날짜 계산
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);

        // 일주일 전 이전에 소프트 딜리트된 리뷰 조회
        List<Comment> commentsToDelete = commentRepository.findDeletedCommentsBefore(oneWeekAgo);

        if (commentsToDelete.isEmpty()) {
            log.info("제거할 댓글이 없습니다.");
            return;
        }
        for (Comment comment : commentsToDelete) {
            try {
                log.info("리뷰 삭제 시도: id={}", comment.getCommentId());
                commentRepository.delete(comment);
                log.info("리뷰 삭제 성공: id={}", comment.getCommentId());
            } catch (Exception e) {
                log.error("리뷰 삭제 실패: id={}, 이유={}", comment.getDeletedAt(), e.getMessage());
            }
        }
        log.info("삭제가 완료되었습니다.\n");
    }
}
