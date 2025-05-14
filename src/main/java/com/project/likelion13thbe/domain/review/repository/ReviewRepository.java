package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ReviewRepository  extends JpaRepository<Review, Long> {

    @Query("SELECT AVG(r.score) FROM Review r WHERE r.product.id = :productId AND r.deletedAt IS NULL")
    Double findRatingAvgByProductId(@Param("productId") Long productId);//삭제된 상품리뷰평점은 제외

    @Query("SELECT COUNT(r) FROM Review r WHERE r.product.id = :productId AND r.deletedAt IS NULL")
    Integer findReviewCountByProductId(@Param("productId") Long productId); //삭제된 리뷰 제외하고 리뷰 개수

    //삭제 되지 않은 리뷰 중 아이디로 조회
    @Query("SELECT r FROM Review r WHERE r.id = :reviewId AND r.deletedAt IS NULL")
    Optional<Member> findByIdAndNotDeleted(@Param("reviewId") Long reviewId);

    Slice<Review> findByMemberIdAndIdLessThanOrderByCreatedAtDesc(Long memberId, Long id,Pageable pageable);//내 리뷰 조회 최신순 정렬
    Slice<Review> findByProductIdAndIdLessThanOrderByCreatedAtDesc(Long productId, Long id,Pageable pageable); // 상품 리뷰 목록 조회

}

