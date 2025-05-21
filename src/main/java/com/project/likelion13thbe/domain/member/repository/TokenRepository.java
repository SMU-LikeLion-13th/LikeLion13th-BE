package com.project.likelion13thbe.domain.member.repository;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("SELECT m FROM Token m WHERE m.email = :email")
    Optional<Token> findByEmail(@Param("email") String email);
}
