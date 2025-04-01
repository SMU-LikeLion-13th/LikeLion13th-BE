package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
}
