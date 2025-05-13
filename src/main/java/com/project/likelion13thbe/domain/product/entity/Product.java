package com.project.likelion13thbe.domain.product.entity;

import com.project.likelion13thbe.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "rating")
    private int rating;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
