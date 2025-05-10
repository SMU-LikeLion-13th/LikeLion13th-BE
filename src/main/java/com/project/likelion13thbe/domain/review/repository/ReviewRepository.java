package com.project.likelion13thbe.domain.review.repository;


import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 소프트 delete
    @Query("SELECT r FROM Review r WHERE r.reviewId = :reviewId AND r.deletedAt IS null ")
    Optional<Review> findByIdNotDeleted(@Param("reviewId") Long reviewId);
}
