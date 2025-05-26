package com.project.likelion13thbe.domain.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class OrderResDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderCreateResDTO {
        private Long id;
        private LocalDateTime createdAt;
    }
}
