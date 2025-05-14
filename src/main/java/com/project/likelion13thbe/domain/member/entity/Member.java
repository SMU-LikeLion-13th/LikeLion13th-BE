package com.project.likelion13thbe.domain.member.entity;

import com.project.likelion13thbe.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private Long password;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public void updatePassword(Long newPassword) {
        this.password = newPassword;
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
