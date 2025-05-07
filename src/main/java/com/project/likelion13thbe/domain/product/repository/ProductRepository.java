package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Slice<Product> findAllByProductIdLessThanOrderByProductIdDesc(Long productId, Pageable pageable);
}
