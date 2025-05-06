package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {
}
