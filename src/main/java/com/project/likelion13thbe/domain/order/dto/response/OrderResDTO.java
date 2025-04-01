package com.project.likelion13thbe.domain.order.dto.response;
import lombok.Getter;
import lombok.Setter;

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
}