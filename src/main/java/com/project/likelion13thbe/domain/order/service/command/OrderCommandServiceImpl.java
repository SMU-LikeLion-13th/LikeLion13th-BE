package com.project.likelion13thbe.domain.order.service.command;

import com.project.likelion13thbe.domain.order.converter.OrderConverter;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.entity.Order;
import com.project.likelion13thbe.domain.order.repository.OrderRepository;
import com.project.likelion13thbe.domain.order.service.command.OrderCommandServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;

    public OrderResDTO.OrderCreateResDTO createOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        Order order = OrderConverter.toOrder(orderCreateReqDTO);

        orderRepository.save(order);

        return OrderConverter.toOrderResDTO(order);
    }

    public void updateOrderStatus(String status, OrderReqDTO.updateOrderStatusDTO dto) {

        Order order = orderRepository.findByIdAndNotDeleted(id).orElseThrow();

        order.updateOrderStatus(status);
    }

}
