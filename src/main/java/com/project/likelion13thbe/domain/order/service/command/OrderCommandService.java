package com.project.likelion13thbe.domain.order.service.command;

import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.order.converter.OrderConverter;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.repository.OrderRepository;
import com.project.likelion13thbe.domain.order.entity.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCommandService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public OrderResDTO.OrderCreateResDTO createOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        Order order = OrderConverter.toOrder(orderCreateReqDTO, memberRepository);

        orderRepository.save(order);

        return OrderConverter.toOrderResponseDTO(order);
    }

}
