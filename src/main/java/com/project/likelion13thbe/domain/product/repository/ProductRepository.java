package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Slice<Product> findAllByProductIdLessThanOrderByProductIdDesc(Long productId, Pageable pageable);
    @Query("SELECT m FROM Product m WHERE m.productId = :productId AND m.deletedAt IS NULL")
    Optional<Product> findByProductIdAndNotDeleted(@Param("productId") Long productId);

}
