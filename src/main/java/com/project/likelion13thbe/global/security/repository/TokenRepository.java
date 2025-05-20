package com.project.likelion13thbe.global.security.repository;

import com.project.likelion13thbe.global.security.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByEmail(String email);

    void deleteByEmail(String email);
}