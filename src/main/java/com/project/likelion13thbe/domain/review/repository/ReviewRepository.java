package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
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
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.product.productId = :productId AND r.deletedAt IS NULL")
    List<Review> findAllReviewsByProductIdAndNotDeleted(Long productId);

    // 이런 식으로 안하는 것을 알고는 있지만 일단 토큰 방식을 몰라서 내 리뷰 조회가 작동만 할 수 있게 작성했습니다
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.member.memberId = :memberId AND r.deletedAt IS NULL")
    List<Review> findAllReviewsByMemberIdAndNotDeleted(Long memberId);

    // 삭제되지 않은 리뷰를 리뷰 아이디로 조회
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.reviewId = :reviewId AND r.deletedAt IS NULL")
    Optional<Review> findByReviewIdAndNotDeleted(@Param("reviewId") Long reviewId);

    // 소프트 딜리트된 지 7일 지난 리뷰 조회
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.deletedAt IS NOT NULL AND r.deletedAt <= :oneWeekAgo")
    List<Review> findDeletedReviewsBefore(@Param("oneWeekAgo") LocalDateTime oneWeekAgo);

    // cursor
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.product.productId = :reviewId AND r.reviewId < :cursor AND r.deletedAt IS NULL " +
            "ORDER BY r.reviewId DESC")
    Slice<Review> findAllReviewByProductIdLessThanOrderByReviewIdDesc(
            @Param("reviewId") Long reviewId, @Param("cursor") Long cursor, Pageable pageable);

}
