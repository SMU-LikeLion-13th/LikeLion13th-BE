package com.project.likelion13thbe.global.S3;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
@Tag(name = "S3 관련")
public class S3Controller {

    private final S3PresignedService s3PresignedService;

    // Presigned PUT URL 생성
    @Operation(summary = "업로드 전용 URL 생성")
    @GetMapping("/upload-url")
    public CustomResponse<String> getUploadUrl(@RequestParam String fileName) {
        String url = s3PresignedService.generatePresignedPutUrl(fileName);
        return CustomResponse.onSuccess(url);
    }

    // Presigned GET URL 생성
    @Operation(summary = "다운로드 전용 URL 생성")
    @GetMapping("/download-url")
    public CustomResponse<String> getDownloadUrl(@RequestParam String fileName) {
        String url = s3PresignedService.generatePresignedGetUrl(fileName);
        return CustomResponse.onSuccess(url);
    }
}
