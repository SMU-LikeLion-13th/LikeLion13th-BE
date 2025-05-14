package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.product.dto.ProductDetailDTO;
import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new com.project.likelion13thbe.domain.product.dto.ProductDetailDTO(" +
            "p, " +
            "COALESCE(AVG(r.rating), 0.0), " +
            "COUNT(r)" +
            ") " +
            "FROM Product p " +
            "LEFT JOIN Review r ON r.product.productId = p.productId " +
            "WHERE r.deletedAt IS NULL " +
            "GROUP BY p")
    List<ProductDetailDTO> findAllProductsWithReviewStatsAndNotDeleted();

    @Query("SELECT new com.project.likelion13thbe.domain.product.dto.ProductDetailDTO(" +
            "p, " +
            "COALESCE(AVG(r.rating), 0.0), " +
            "COUNT(r)" +
            ") " +
            "FROM Product p " +
            "LEFT JOIN Review r ON r.product.productId = p.productId " +
            "WHERE p.productId = :productId AND r.deletedAt IS NULL " +
            "GROUP BY p")
    Optional<ProductDetailDTO> findProductWithReviewStatsAndNotDeleted(Long productId);

    // cursor
    @Query("SELECT new com.project.likelion13thbe.domain.product.dto.ProductDetailDTO(" +
            "p, " +
            "COALESCE(AVG(r.rating), 0.0), " +
            "COUNT(r)" +
            ") " +
            "FROM Product p " +
            "LEFT JOIN Review r ON r.product.productId = p.productId " +
            "WHERE r.reviewId < :cursor AND r.deletedAt IS NULL " +
            "GROUP BY p " +
            "ORDER BY p.productId DESC")
    Slice<ProductDetailDTO> findAllByProductIdLessThanOrderByProductIdDesc(@Param("cursor") Long cursor, Pageable pageable);


}
