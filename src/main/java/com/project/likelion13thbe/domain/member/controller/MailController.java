package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.service.command.MailService;
import com.project.likelion13thbe.domain.product.dto.request.ProductReqDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @Operation(summary = "이메일 인증 체크 api", description = "이메일 인증 체크 API입니다.")
    @ResponseBody
    @PostMapping("/api/v1/emails")
    public String emailCheck(
            @RequestBody ProductReqDTO.MailDTO mailDTO) throws MessagingException {

        return mailService.sendSimpleMessage(mailDTO.email());
    }
}
