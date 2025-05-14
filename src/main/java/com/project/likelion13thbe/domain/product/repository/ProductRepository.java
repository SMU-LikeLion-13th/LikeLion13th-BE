package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.product.dto.ProductReviewDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("""
    SELECT new com.project.likelion13thbe.domain.product.dto.ProductReviewDTO(
        p,
        COALESCE(CAST(AVG(r.rate) AS double), 0.0),
        COUNT(r)
    )
    FROM Product p
    LEFT JOIN Review r ON r.product.id = p.id AND r.deletedAt IS NULL
    GROUP BY p
""")
    List<ProductReviewDTO> findAllProductsWithReviewStats();
}