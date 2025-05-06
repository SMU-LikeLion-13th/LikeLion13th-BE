package com.project.likelion13thbe.domain.order.dto.request;

import com.project.likelion13thbe.domain.member.entity.Member;

public class OrderReqDTO {
    public record OrderCreateReqDTO (
            Long totalPrice,
            Long amount,
            Long userId
    ) {}



}
