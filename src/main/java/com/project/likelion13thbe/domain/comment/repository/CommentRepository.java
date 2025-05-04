package com.project.likelion13thbe.domain.comment.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c JOIN FETCH c.member WHERE c.review.id = :reviewId")
    List<Comment> findAllByReviewId(@Param("reviewId") Long reviewId);
}
