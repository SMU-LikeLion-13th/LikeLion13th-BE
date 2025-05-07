package com.project.likelion13thbe.domain.product.entity;

import com.project.likelion13thbe.global.entity.BaseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Tag(name="product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name="product image", nullable = false)
    private String image;

    @Column(name="quantity", nullable = false)
    private Integer quantity;

    @Column(name="product description", nullable = false)
    private String description;

    @Column(name="rating", nullable = false)
    private Integer rating;


}
