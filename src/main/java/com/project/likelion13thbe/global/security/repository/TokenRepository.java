package com.project.likelion13thbe.global.security.repository;

import com.project.likelion13thbe.global.security.entitiy.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TokenRepository extends JpaRepository<Token, String> {
     public Optional<Token> findByEmail(String email) {
    }

}
