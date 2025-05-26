package com.project.likelion13thbe.global.mail.service;

import com.project.likelion13thbe.global.mail.exception.MailErrorCode;
import com.project.likelion13thbe.global.mail.exception.MailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final RedisTemplate<String, String> redisTemplate;

    // 단순 이메일 전송
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        try {
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom("hyunbini02@naver.com");
            mailSender.send(message);
        } catch (MailAuthenticationException e) {
            log.error("SMTP 인증 실패: {}", e.getMessage());
            throw new MailException(MailErrorCode.AUTHENTICATION_FAILED);
        } catch (MailSendException e) {
            log.error("SMTP 서버 연결 실패: {}", e.getMessage());
            throw new MailException(MailErrorCode.CONNECTION_FAILED);
        } catch (MailException e) {
            log.error("메일 전송 오류: {}", e.getMessage());
            throw new MailException(MailErrorCode.UNKNOWN_ERROR);
        } catch (Exception e) {
            log.error("알 수 없는 오류: {}", e.getMessage());
            throw new MailException(MailErrorCode.UNKNOWN_ERROR);
        }
    }

    // 인증코드 이메일로 전송
    public void sendAuthCodeMail(String to) {
        String authCode = generateCode(6);

        String subject = "이메일 인증 코드";
        String text = "인증 코드: " + authCode;

        // Redis에 저장 (TTL: 5 mins)
        redisTemplate.opsForValue().set("authcode:" + to, authCode, 5, TimeUnit.MINUTES);

        sendMail(to, subject, text);
    }

    private String generateCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();

        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }

        return code.toString();
    }
}
