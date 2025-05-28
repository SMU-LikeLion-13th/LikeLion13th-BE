package com.project.likelion13thbe.domain.order.service.command;

import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;

public interface OrderCommandService {
    OrderResDTO.OrderCreateResDTO createOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO);
    void updateOrder(Long id, OrderReqDTO.OrderUpdateReqDTO dto);
    void deleteOrder(Long id);
    void updateOrderByMemberIdAndId(Long memberId, Long id, OrderReqDTO.OrderUpdateReqDTO dto);
    void deleteOrderByMemberId(Long memberId);
}
