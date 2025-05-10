package com.project.likelion13thbe.domain.product.repository;


import com.project.likelion13thbe.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.deletedAt IS null")
    Optional<Product> findByIdNotDeleted(@Param("id") Long id);
}
