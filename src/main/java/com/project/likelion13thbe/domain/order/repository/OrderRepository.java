package com.project.likelion13thbe.domain.order.repository;

import com.project.likelion13thbe.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUsername(Long id, String username);
    Optional<Order> findByUsername(String username);
}
