package com.project.likelion13thbe.domain.order.dto.request;

import lombok.Getter;

public class OrderReqDTO {

    @Getter
    public static class OrderCreateReqDTO {
        private String name;
        private Integer quantity;
        private String status;
    }
}
