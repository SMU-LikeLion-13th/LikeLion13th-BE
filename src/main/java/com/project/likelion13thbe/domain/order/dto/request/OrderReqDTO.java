package com.project.likelion13thbe.domain.order.dto.request;

import lombok.Getter;

public class OrderReqDTO {
    @Getter
    public static class OrderCreateReqDTO {
        private Short quantity;
        private String status;

        // member 테이블 외래 PK
        private Long memberId;

        // product 테이블 외래 PK
        private Long productId;
    }

    @Getter
    public static class OrderUpdateReqDTO {
        private Short quantity;
        private String status;

        private Long memberId;
        private Long productId;
    }
}
