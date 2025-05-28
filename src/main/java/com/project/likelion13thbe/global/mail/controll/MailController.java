package com.project.likelion13thbe.global.mail.controll;

import com.project.likelion13thbe.global.mail.dto.MailDTO;
import com.project.likelion13thbe.global.mail.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @PostMapping("/emailAuthentication")
    public ResponseEntity<String> sendEmail(@RequestBody @Valid MailDTO mailDTO) throws MessagingException {
        mailService.sendSimpleMessage(mailDTO.getEmail()); // 인증코드 전송 및 내부 저장
        return ResponseEntity.ok("인증 메일이 전송되었습니다.");
    }

}
