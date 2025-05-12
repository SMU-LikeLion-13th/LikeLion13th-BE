package com.project.likelion13thbe.domain.comment.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
