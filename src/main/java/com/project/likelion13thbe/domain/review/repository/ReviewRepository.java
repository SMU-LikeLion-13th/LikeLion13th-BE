package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select avg(r.rate) from Review r where r.product.id = :productId")
    Double findRatingAvgByProductId(@Param("productId") Long productId);
    @Query("select count(r) from Review r where r.product.id = :productId")
    Integer findReviewCountByProductId(@Param("productId") Long productId);

    @Query("select r from Review r join fetch r.product where r.product.id =: productIds"
    )
    List<Review> findAllByProductIdIn(@Param("productIds") List<Long> productIds);

}
