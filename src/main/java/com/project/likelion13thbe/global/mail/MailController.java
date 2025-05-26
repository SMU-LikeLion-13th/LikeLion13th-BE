package com.project.likelion13thbe.global.mail;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail-verifications")
@Tag(name = "SMTP 인증")
public class MailController {

    private final MailService mailService;

    @GetMapping("/request")
    public CustomResponse<String> sendVerificationCode(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        mailService.sendVerificationCode();
        return CustomResponse.onSuccess("인증 코드가 전송되었습니다.");
    }
}
