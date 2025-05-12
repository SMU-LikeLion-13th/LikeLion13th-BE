package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.comment.entity.Comment;
import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.id = :id AND r.deletedAt IS NULL")
    Optional<Review> findByIdAndNotDeleted(@Param("id") Long reviewId);

    @Query("SELECT r FROM Review r WHERE r.id < :id AND r.product.id = :productId AND r.deletedAt IS NULL ORDER BY r.id DESC")
    Slice<Review> findAllByIdLessThanOrderByIdDescAndNotDeleted(
            @Param("productId") Long productId, @Param("id") Long reviewId, Pageable pageable);
}
