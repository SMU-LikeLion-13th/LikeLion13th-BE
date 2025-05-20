package com.project.likelion13thbe.domain.order.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class OrderReqDTO {

    @Getter
    public static class OrderCreateReqDTO {
        private String name;
        private Integer quantity;
        private String status;
    }

    @Getter
    public static class updateOrderStatus {
        @Schema(description = "주문 상태 변경" ,example = "배송중")
        @NotBlank
        private String status;
    }
}
