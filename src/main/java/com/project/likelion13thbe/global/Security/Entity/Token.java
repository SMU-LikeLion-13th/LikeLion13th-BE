package com.project.likelion13thbe.global.Security.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "token")
public class Token {
    // Refresh Token을 저장하는 엔티티
    @Id
    private String email;
    private String token;
}
