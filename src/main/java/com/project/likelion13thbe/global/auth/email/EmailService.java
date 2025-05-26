package com.project.likelion13thbe.global.auth.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final EmailTemplateBuilder emailTemplateBuilder;

    // 임시 비번 이메일로 전송
    public void sendTempPassword(String email, String encodedPassword) {
        String html = emailTemplateBuilder.buildTempPasswordHtml(encodedPassword);
        sendHtmlEmail(email, "인증번호 안내", html);
    }

    //이메일 전송 전용 메서드
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();  // MIME 형식의 이메일 객체 생성
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");  // 도우미 클래스: 텍스트 설정 쉽게

            helper.setTo(to);               // 수신자 설정
            helper.setSubject(subject);     // 메일 제목 설정
            helper.setFrom("tjgustjr16@naver.com");  // 발신자 설정 (SMTP 계정과 동일해야 함)
            helper.setText(htmlBody, true); // 본문 설정 (true → HTML 형식)

            javaMailSender.send(message);       // 메일 전송
        } catch (MessagingException e) {
            throw new RuntimeException("메일 전송 실패", e);  // 예외 발생 시 사용자 정의 예외로 감싸기
        }
    }
}
