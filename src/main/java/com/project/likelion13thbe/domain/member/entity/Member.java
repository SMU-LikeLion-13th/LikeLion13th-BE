package com.project.likelion13thbe.domain.member.entity;


import com.project.likelion13thbe.domain.member.type.Role;
import com.project.likelion13thbe.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
// JPA가 사용할 수 있도록 안전한 생성자 구조를 보장하고
// 개발자는 Builder로만 객체를 생성하도록 유도하는 구조 대충 외부에서 못건들게함
public class Member extends BaseEntity {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 자동으로 기본키를 생성하도록 함
    private Long id;

    @Column(name = "name" ,nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private String image;

    @Column(name = "age")
    private Integer age;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;



    //비밀번호 변경 메서드
    public void updatePassword(String newPassword){this.password = newPassword;}

    // soft delete 메서드
    public void delete(){this.deletedAt = LocalDateTime.now();}



}
