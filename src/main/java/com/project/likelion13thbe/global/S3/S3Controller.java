package com.project.likelion13thbe.global.S3;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.customUserDetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @Operation(summary = "공개 파일 업로드 전용 URL 생성")
    @GetMapping("/public-upload-url")
    public CustomResponse<String> getPublicUploadUrl(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam String fileName
    ) {
        String url = s3PresignedService.generatePublicPresignedPutUrl(customUserDetails.getUsername(), fileName);
        return CustomResponse.onSuccess(url);
    }

    @Operation(summary = "비공개 파일 업로드 전용 URL 생성")
    @GetMapping("/private-upload-url")
    public CustomResponse<String> getPrivateUploadUrl(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam String fileName
    ) {
        String url = s3PresignedService.generatePrivatePresignedPutUrl(customUserDetails.getUsername(), fileName);
        return CustomResponse.onSuccess(url);
    }

    @Operation(summary = "공개 파일 다운로드 전용 URL 생성")
    @GetMapping("/public-download-url")
    public CustomResponse<String> getPublicDownloadUrl(
            @RequestParam String fileName
    ) {
        String uploaderEmail = "string"; // 멤버 DB 기본 이메일
        String url = s3PresignedService.generatePublicPresignedGetUrl(uploaderEmail, fileName);
        return CustomResponse.onSuccess(url);
    }

    @Operation(summary = "비공개 파일 다운로드 전용 URL 생성")
    @GetMapping("/private-download-url")
    public CustomResponse<String> getPrivateDownloadUrl(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestParam String fileName
    ) {
        String url = s3PresignedService.generatePrivatePresignedGetUrl(customUserDetails.getUsername(), fileName);
        return CustomResponse.onSuccess(url);
    }

}
