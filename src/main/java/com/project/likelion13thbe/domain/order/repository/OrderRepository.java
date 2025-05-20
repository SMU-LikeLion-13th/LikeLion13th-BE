package com.project.likelion13thbe.domain.order.repository;

import com.project.likelion13thbe.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :id and o.deletedAt IS null ")
    Optional<Order> findIdAndNOtDeleted(@Param("id") Long id);
}
