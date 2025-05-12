package com.project.likelion13thbe.domain.comment.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.id = :id AND c.deletedAt IS NULL")
    Optional<Comment> findByIdAndNotDeleted(@Param("id") Long commentId);

    @Query("SELECT c FROM Comment c WHERE c.review.id = :reviewId AND c.id < :id AND c.deletedAt IS NULL ORDER BY c.id DESC")
    Slice<Comment> findAllByIdLessThanOrderByIdDescAndNotDeleted(@Param("reviewId") Long reviewId, @Param("id") Long commentId, Pageable pageable);
}
