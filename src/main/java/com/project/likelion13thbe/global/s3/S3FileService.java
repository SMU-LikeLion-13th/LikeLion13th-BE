package com.project.likelion13thbe.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3FileService {

    private final AmazonS3 s3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String saveFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        // 파일 저장
        s3.putObject(bucket, originalFilename, file.getInputStream(), metadata);

        // 파일이 저장된 url return
        return s3.getUrl(bucket, originalFilename).toString();
    }

    public UrlResource getFileAsResource(String fileName) {
        return new UrlResource(s3.getUrl(bucket, fileName));
    }

    public void deleteFile(String fileName) {
        s3.deleteObject(bucket, fileName);
    }

}
