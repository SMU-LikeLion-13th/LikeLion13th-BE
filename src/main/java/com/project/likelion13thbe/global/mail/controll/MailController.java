package com.project.likelion13thbe.global.mail.controll;

import com.project.likelion13thbe.global.mail.dto.MailDTO;
import com.project.likelion13thbe.global.mail.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @ResponseBody
    @PostMapping("/emailAuthentication")
    public String emailCheck(@RequestBody MailDTO mailDTO) throws MessagingException, UnsupportedEncodingException {
        String authCode = mailService.sendSimpleMessage(mailDTO.getEmail());
        return authCode; // Response body에 값을 반환
    }
}
