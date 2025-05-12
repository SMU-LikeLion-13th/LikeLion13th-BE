package com.project.likelion13thbe.domain.comment.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.review.reviewId = :reviewId AND c.deletedAt IS NULL")
    List<Comment> findCommentByReviewIdAndNotDeleted(Long reviewId);

    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.commentId = :commentId AND c.deletedAt IS NULL")
    Optional<Comment> findByCommentIdAndNotDeleted(@Param("commentId") Long commentId);

    // 소프트 딜리트된 지 7일 지난 댓글 조회
    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.deletedAt IS NOT NULL AND c.deletedAt <= :oneWeekAgo")
    List<Comment> findDeletedCommentsBefore(@Param("oneWeekAgo") LocalDateTime oneWeekAgo);

    @Query("SELECT c " +
            "FROM Comment c " +
            "WHERE c.review.reviewId = :reviewId AND c.commentId < :cursor AND c.deletedAt IS NULL " +
            "ORDER BY c.commentId DESC")
    Slice<Comment> findAllCommentByReivewIdLessThanOrderByCommentIdDesc(
            @Param("reviewId") Long reviewId, @Param("cursor") Long cursor, Pageable pageable);

}
