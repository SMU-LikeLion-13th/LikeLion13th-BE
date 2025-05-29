package com.project.likelion13thbe.global.S3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3PresignedService {

    private final S3Presigner s3Presigner;
    private final S3Repository s3Repository;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    // 공개 파일 업로드 URL
    public String generatePublicPresignedPutUrl(String email, String fileName) {

        String uniqueFileName = getUniqueFileName(fileName);

        // 일단 무조건 업로드 한다고 가정
        s3Repository.save(S3File.builder()
                .email(email)
                .uniqueFileName(uniqueFileName)
                .originalFileName(fileName)
                .build());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(uniqueFileName) // 파일 이름 중복 방지
                .acl(ObjectCannedACL.PUBLIC_READ) // 중요하지 않은 정보라면 인증정보 없이 업로드?
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(
                PutObjectPresignRequest.builder()
                        .putObjectRequest(putObjectRequest)
                        .signatureDuration(Duration.ofMinutes(10))
                        .build()
        );

        return presignedRequest.url().toString();
    }

    // 비공개 파일 업로드 URL
    public String generatePrivatePresignedPutUrl(String email, String fileName) {

        String uniqueFileName = getUniqueFileName(fileName);

        // 일단 무조건 업로드 한다고 가정
        s3Repository.save(S3File.builder()
                .email(email)
                .uniqueFileName(uniqueFileName)
                .originalFileName(fileName)
                .build());

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(uniqueFileName)
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(
                PutObjectPresignRequest.builder()
                        .putObjectRequest(putObjectRequest)
                        .signatureDuration(Duration.ofMinutes(10))
                        .build()
        );

        return presignedRequest.url().toString();
    }

    // 누구나 접근 가능한 공개 파일에 대한 다운로드 링크
    public String generatePublicPresignedGetUrl(String uploaderEmail, String fileName) {
        S3File s3File = s3Repository.findByEmailAndOriginalFileName(uploaderEmail, fileName)
                .orElseThrow();

        return String.format("https://s3.%s.amazonaws.com/%s/%s", region, bucket, s3File.getUniqueFileName());
    }

    // 비공개 파일에 대한 인증을 포함한 다운로드 링크
    public String generatePrivatePresignedGetUrl(String email, String fileName) {

        S3File s3File = s3Repository.findByEmailAndOriginalFileName(email, fileName)
                .orElseThrow();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(s3File.getUniqueFileName())
//                .responseContentDisposition("attachment") // 이러면 바로 다운로드가?
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(
                GetObjectPresignRequest.builder()
                        .getObjectRequest(getObjectRequest)
                        .signatureDuration(Duration.ofMinutes(10))
                        .build()
        );

        return presignedRequest.url().toString();
    }

    // 유니크 파일 아이디
    private String getUniqueFileName(String originalFileName) {
        return UUID.randomUUID() + "_" + originalFileName;
    }
}