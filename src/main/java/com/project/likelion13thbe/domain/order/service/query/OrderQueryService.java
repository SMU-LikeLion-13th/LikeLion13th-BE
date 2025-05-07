package com.project.likelion13thbe.domain.order.service.query;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.order.converter.OrderConverter;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.entity.Order;
import com.project.likelion13thbe.domain.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderQueryService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    public OrderResDTO.OrderPreviewResDTO getOrder() {
        Order order = orderRepository.findById(1L).get();

        return OrderConverter.toOrderPreviewDTO(order);
    }
}
