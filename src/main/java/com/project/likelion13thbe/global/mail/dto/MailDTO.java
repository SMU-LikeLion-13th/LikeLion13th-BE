package com.project.likelion13thbe.global.mail.dto;

public class MailDTO {
    public record AuthCodeRequestDTO(
            String to
    ) {
    }

    public record AuthCodeVerifyRequestDTO(
            String to,
            String code
    ) {
    }
}
