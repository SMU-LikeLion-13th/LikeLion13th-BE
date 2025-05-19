package com.project.likelion13thbe.global.security.dto;

public class SecurityDTO {

    public record LoginRequestDTO(
            String email,
            String password
    ) {
    }
}
