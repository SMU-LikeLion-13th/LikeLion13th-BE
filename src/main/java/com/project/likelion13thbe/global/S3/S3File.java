package com.project.likelion13thbe.global.S3;

import com.project.likelion13thbe.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "s3File")
public class S3File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(name = "unique_file_name", nullable = false)
    private String uniqueFileName;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(name = "email", nullable = false)
    private String email;

}
