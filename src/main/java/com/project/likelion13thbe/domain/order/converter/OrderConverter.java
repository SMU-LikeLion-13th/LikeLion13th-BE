package com.project.likelion13thbe.domain.order.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.entity.Order;
import com.project.likelion13thbe.domain.product.entity.Product;

public class OrderConverter {
    public static Order toOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO, Member member, Product product) {
        return Order.builder()
                .quantity(orderCreateReqDTO.getQuantity())
                .status(orderCreateReqDTO.getStatus())
                .member(member)
                .product(product)
                .build();
    }
    public static OrderResDTO.OrderCreateResDTO toOrderResDTO(Order order) {
        return OrderResDTO.OrderCreateResDTO.builder()
                .id(order.getId())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
