package com.project.likelion13thbe.global.s3;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class S3FileController {

    private final S3FileService s3FileService;

    @PostMapping("/upload")
    public CustomResponse<?> uploadFile(@RequestPart MultipartFile file) {
        try {
            String fileUrl = s3FileService.saveFile(file);
            return CustomResponse.onSuccess(HttpStatus.OK, fileUrl); // 업로드된 파일의 S3 URL 리턴
        } catch (IOException e) {
            return CustomResponse.onSuccess(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패: " + e.getMessage());
        }
    }

    @GetMapping("/download")
    public ResponseEntity<UrlResource> downloadFile(@RequestParam String fileName) {
        UrlResource resource = s3FileService.getFileAsResource(fileName);

        String contentDisposition = "attachment; filename=\"" + fileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @DeleteMapping("/{filename}")
    public CustomResponse<String> deleteFile(@PathVariable String filename) {
        s3FileService.deleteFile(filename);
        return CustomResponse.onSuccess(HttpStatus.OK, "파일 삭제 완료");
    }
}
