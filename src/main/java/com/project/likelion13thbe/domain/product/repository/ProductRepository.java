package com.project.likelion13thbe.domain.product.repository;

import com.project.likelion13thbe.domain.order.entity.Order;
import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
