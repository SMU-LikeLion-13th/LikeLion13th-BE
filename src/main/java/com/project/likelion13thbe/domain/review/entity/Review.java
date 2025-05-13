package com.project.likelion13thbe.domain.review.entity;

import com.project.likelion13thbe.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

//@Entity
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name = "starRating", nullable = false)
    private String starRating;

    @Column(name = "likeCount", nullable = false)
    private String likeCount;
}
