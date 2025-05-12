package com.project.likelion13thbe.domain.order.converter;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.entity.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConverter {

    public static Order toOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO,
                                MemberRepository memberRepository) {

        Member member = memberRepository.findById(orderCreateReqDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        return Order.builder()
                .totalPrice(orderCreateReqDTO.totalPrice())
                .amount(orderCreateReqDTO.amount())
                .member(member)
                .build();
    }

    public static OrderResDTO.OrderCreateResDTO toOrderResponseDTO (Order order) {
        return new OrderResDTO.OrderCreateResDTO(
                order.getOrderId(),
                order.getCreatedAt()
        );
    }

    public static OrderResDTO.OrderPreviewResDTO toOrderPreviewDTO (Order order) {
        return new OrderResDTO.OrderPreviewResDTO(
                order.getOrderId(),
                order.getTotalPrice(),
                order.getAmount(),
                order.getMember().getMemberId()
        );
    }
}
