package com.project.likelion13thbe.domain.order.repository;

import com.project.likelion13thbe.domain.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
}
