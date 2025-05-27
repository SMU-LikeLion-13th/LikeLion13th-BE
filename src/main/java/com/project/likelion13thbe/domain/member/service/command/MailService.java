package com.project.likelion13thbe.domain.member.service.command;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    //메일을 보내주는 클래스
    private final JavaMailSender javaMailSender;
    //인증 번호를 보낼 메일
    private static final String senderEmail = "hanmin2747@gmail.com";

    //인증코드 생성 로직
    public String createNumber() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);
            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97));

                case 1 -> key.append((char) (random.nextInt(26) + 65));

                case 2 -> key.append(random.nextInt(10));
            }
        }
        return key.toString();
    }

    public MimeMessage createMail(String mail, String number) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        //보내는 이메일
        message.setFrom(senderEmail);
        //보내는 메일에 들어가는 내용들
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("이메일 인증");
        String body = "";
        body += "<h3>요청하신 인증 번호입니다.</h3>";
        body += "<h1>" + number + "</h1>";
        message.setText(body, "UTF-8", "html");
        //만든 메일 반환
        return message;
    }

    public String sendSimpleMessage(String sendEmail) throws MessagingException {
        String number = createNumber();  //랜덤 인증번호 생성
        //메일 생성
        MimeMessage message = createMail(sendEmail, number);
        try {
            //메일 보내기
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("메일 발송 중 오류가 발생했습니다.");
        }
        //보낸 인증 번호를 반환
        return number;
    }
}
