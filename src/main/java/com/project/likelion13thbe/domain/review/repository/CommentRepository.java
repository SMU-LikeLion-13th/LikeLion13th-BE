package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT m FROM Comment m WHERE m.commentId = :commentId AND m.deletedAt IS NULL")
    Optional<Comment> findByCommentIdAndNotDeleted(@Param("commentId") Long commentId);
}
