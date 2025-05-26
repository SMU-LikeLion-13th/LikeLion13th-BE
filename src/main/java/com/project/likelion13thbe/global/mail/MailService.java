package com.project.likelion13thbe.global.mail;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, String> redisTemplate;

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

    public void sendTempPassword(String email, String tempPassword) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setFrom("dlrbdjs7@naver.com");
            helper.setTo(email); // <-- 전달받은 수신자 이메일
            helper.setSubject("임시 비밀번호 발급");

            // 인증 코드를 포함한 HTML 콘텐츠 생성
            String content = """
                    <!DOCTYPE html>
                    <html>
                    <body>
                    <div style="margin:100px;">
                        <h1> 안녕하세요 :) </h1>
                        <br>
                        <p>아래 임시 비밀번호로 로그인해주세요.</p>
                        <div align="center" style="border:1px solid black; padding:10px;">
                            <h2>%s</h2>
                        </div>
                        <p>비밀번호는 1회만 사용가능합니다 (희망사항)</p>
                    </div>
                    </body>
                    </html>
                    """.formatted(tempPassword);

            helper.setText(content, true);
            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공! 대상: {}, 비밀번호: {}", email, tempPassword);
        } catch (Exception e) {
            log.error("메일 발송 실패!", e);
            throw new RuntimeException("메일 발송 중 오류 발생", e);
        }
    }

    public void sendVerificationTokenURL(String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            helper.setFrom("dlrbdjs7@naver.com");
            helper.setTo("dlrbdjs7@naver.com"); // <-- 전달받은 수신자 이메일
            helper.setSubject("이메일 인증용 링크");

            String randomToken = UUID.randomUUID().toString();
            String redisKey = "verification_token : " + randomToken;

            redisTemplate.opsForValue().set(redisKey, email, 300000, TimeUnit.MILLISECONDS);

            String tokenURL = "http://localhost:8080/mail-verifications/validation?token=" + randomToken;
            // 인증 코드를 포함한 HTML 콘텐츠 생성
            String content = """
                    <!DOCTYPE html>
                    <html>
                    <body>
                    <div style="margin:100px;">
                        <h1> 안녕하세요 :) </h1>
                        <br>
                        <a>이메일 인증을 완료하려면 아래 URL을 클릭해주세요.</a>
                        <div align="center" style="border:1px solid black; padding:10px;">
                            <h2>
                              <a href="%s" target="_blank">%s</a>
                            </h2>
                        </div>
                        <p>링크의 유효기간은 5분입니다</p>
                    </div>
                    </body>
                    </html>
                    """.formatted(tokenURL, tokenURL);

            helper.setText(content, true);
            javaMailSender.send(mimeMessage);

            log.info("메일 발송 성공! 대상: {}, URL: {}", email, tokenURL);
        } catch (Exception e) {
            log.error("메일 발송 실패!", e);
            throw new RuntimeException("메일 발송 중 오류 발생", e);
        }
    }

    public String validateEmail(String token) {
        String redisKey = "verification_token : " + token;
        String email = redisTemplate.opsForValue().get(redisKey);

        if (email == null) {
            return "잘못된 링크";
        }

        // 멤버객체에 인증완료 ENUM을 할 수 있겠군요
        redisTemplate.delete(redisKey);
        return "인증이 완료되었습니다.";
    }
}

