package com.project.likelion13thbe.global.mail.controller;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.mail.dto.MailDTO;
import com.project.likelion13thbe.global.mail.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Mail", description = "메일 관련 API")
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/auth-code")
    public CustomResponse<String> sendAuthCode(@RequestBody MailDTO.AuthCodeRequestDTO authCodeRequestDTO) {
        mailService.sendAuthCodeMail(authCodeRequestDTO.to());
        return CustomResponse.onSuccess(HttpStatus.OK, "인증 코드 전송 완료");
    }
}