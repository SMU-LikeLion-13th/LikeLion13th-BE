package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long> {
    @Query("SELECT AVG(s.score) FROM Review s WHERE s.product.id = :productId")
    Double findRatingAvgByProductId(@Param("productId") Long productId);//

    @Query("SELECT COUNT(r) FROM Review r WHERE r.product.id = :productId")
    Integer findReviewCountByProductId(@Param("productId") Long productId);


    Slice<Review> findByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable); //내 리뷰 조회 최신순 정렬

}

