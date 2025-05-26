package com.project.likelion13thbe.domain.review.repository;

import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findById(long id);
    Optional<Review> findByUsername(String username);
    Optional<Review> findByIdAndUsername(long id, String username);

}
