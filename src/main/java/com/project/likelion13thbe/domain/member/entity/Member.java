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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "is_temp_password")
    private Boolean isTempPassword = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void delete() {this.deletedAt = LocalDateTime.now();}

    public void isTempPassword() {
        this.isTempPassword = true;
    }
    public void isNotTempPassword() {
        this.isTempPassword = false;
    }
}
