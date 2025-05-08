package com.project.likelion13thbe.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    private long userId;

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
