package com.project.likelion13thbe.domain.order.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResDTO {
    public record TestDTO(
            Long id,
            String content

    ) {}

    public record OrderCreateResDTO(
            Long orderId,
            LocalDateTime createdAt
    ) {}

    public record OrderPreviewResDTO(
            Long orderId,
            Long totalPrice,
            Long amount,
            Long memberId
    ) {}

}
