package com.project.likelion13thbe.domain.order.dto.response;

import lombok.*;

import java.time.LocalDateTime;

public class OrderResDTO {
    public record Test1DTO(
            Long id,
            String content
    ) {
    }

    @Getter
    @Setter
    public class Test2DTO {
        private Long id;
        private String content;
    }
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class OrderCreateResDTO {
        private Long id;
        private LocalDateTime createdAt;
    }
}
