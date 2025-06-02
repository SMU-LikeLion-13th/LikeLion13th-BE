package com.project.likelion13thbe.global.S3;

import com.project.likelion13thbe.global.security.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface S3Repository extends JpaRepository<S3File, Long> {
    Optional<S3File> findByEmailAndOriginalFileName(String email, String originalFileName);
}
