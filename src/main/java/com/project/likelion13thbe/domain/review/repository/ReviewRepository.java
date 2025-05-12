package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r JOIN FETCH r.member WHERE r.product.id = :productId")
    List<Review> findAllByProductId(@Param("productId") Long productId);

    @Query("select avg(r.rate) from Review r where r.product.id = :productId AND r.deletedAt IS NULL")
    Double findRatingAvgByProductId(@Param("productId") Long productId);
    @Query("select count(r) from Review r where r.product.id = :productId AND r.deletedAt IS NULL")
    Long findReviewCountByProductId(@Param("productId") Long productId);

    @Query("SELECT r FROM Review r join fetch r.member WHERE r.member.id= :memberId")
    List<Review> findAllByMemberId(@Param("memberId") Long memberId);
}
