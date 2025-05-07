package com.project.likelion13thbe.domain.member.entity;

import com.project.likelion13thbe.global.entity.BaseEntity;
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
    private Long memberId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "social_type", nullable = false)
    private SocialType socialType;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    // soft delete를 위한 필드 추가
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // 비밀번호 변경 메서드
    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    // soft delete 메서드
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }
}
