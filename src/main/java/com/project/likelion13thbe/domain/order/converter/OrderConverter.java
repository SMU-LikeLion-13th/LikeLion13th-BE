package com.project.likelion13thbe.domain.order.converter;

import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {
    public static Order toOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        return Order.builder()
                .name(orderCreateReqDTO.getName())
                .quantity(orderCreateReqDTO.getQuantity())
                .status(orderCreateReqDTO.getStatus())
                .build();
    }
    public static OrderResDTO.OrderCreateResDTO toOrderResDTO(Order order) {
        return OrderResDTO.OrderCreateResDTO.builder()
                .id(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
