package com.project.likelion13thbe.global.mail;

import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/mail-verifications")
@Tag(name = "SMTP 인증")
public class MailController {

    private final MailService mailService;

    @Operation(summary = "인증 번호 보내기 (그냥 메일 보내는 것만 가능)")
    @GetMapping("/request-code")
    public CustomResponse<String> sendVerificationCode(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        mailService.sendVerificationCode();
        return CustomResponse.onSuccess("인증 코드가 전송되었습니다.");
    }

    @Operation(summary = "이메일 인증 링크 보내기")
    @GetMapping("/request-tokenURL")
    public CustomResponse<String> sendVerificationTokenURL() {
        String email = "dlrbdjs7@naver.com";
        mailService.sendVerificationTokenURL(email);
        return CustomResponse.onSuccess("인증 링크가 전송되었습니다.");
    }

    @Operation(summary = "이메일 링크 유효성 판단")
    @GetMapping("/validation")
    public CustomResponse<String> verifyEmailCode(
            @RequestParam String token
    ) {
        return CustomResponse.onSuccess(mailService.validateEmail(token));
    }
}
