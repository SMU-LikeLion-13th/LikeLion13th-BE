package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {
    Slice<Product> findAllByIdLessThanOrderByIdDesc(Long cursor, Pageable pageable);

    // 삭제 되지 않은 상품Id를 찾아서 삭제
    @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.deletedAt IS NULL")
    Optional<Product> findByIdAndNotDeleted(@Param("productId") Long productId);

}
