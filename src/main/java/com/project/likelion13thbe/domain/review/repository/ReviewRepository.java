package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Slice<Review> findAllByReviewIdLessThanOrderByReviewIdDesc(Long reviewId, Pageable pageable);
    @Query("SELECT m FROM Review m WHERE m.reviewId = :productId AND m.deletedAt IS NULL")
    Optional<Review> findByReviewIdAndNotDeleted(@Param("reviewId") Long reviewId);
}
