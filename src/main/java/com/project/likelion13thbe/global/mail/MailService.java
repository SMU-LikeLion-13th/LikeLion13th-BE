package com.project.likelion13thbe.global.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendVerificationCode() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        String code = createVerificationCode();
        String toEmail = "dlrbdjs7@naver.com";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setFrom("dlrbdjs7@naver.com");
            helper.setTo(toEmail); // <-- 전달받은 수신자 이메일
            helper.setSubject("이메일 인증 코드");

            // 인증 코드를 포함한 HTML 콘텐츠 생성
            String content = """
                    <!DOCTYPE html>
                    <html>
                    <body>
                    <div style="margin:100px;">
                        <h1> 안녕하세요 :) </h1>
                        <br>
                        <p>아래 인증 코드를 입력해주세요.</p>
                        <div align="center" style="border:1px solid black; padding:10px;">
                            <h2>%s</h2>
                        </div>
                        <p>해당 코드는 5분간 유효합니다.</p>
                    </div>
                    </body>
                    </html>
                    """.formatted(code);

            helper.setText(content, true);
            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공! 대상: {}, 코드: {}", toEmail, code);

        } catch (Exception e) {
            log.error("메일 발송 실패!", e);
            throw new RuntimeException("메일 발송 중 오류 발생", e);
        }
    }

    // 내부 사용 메서드
    private String createVerificationCode() {
        SecureRandom random = new SecureRandom();
        return String.format("%06d", random.nextInt(1000000));
    }

}

