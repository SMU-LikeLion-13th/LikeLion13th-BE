package com.project.likelion13thbe.domain.order.service.command;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.order.converter.OrderConverter;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.entity.Order;
import com.project.likelion13thbe.domain.order.repository.OrderRepository;
import com.project.likelion13thbe.domain.product.entity.Product;
import com.project.likelion13thbe.domain.product.repository.ProductRepository;
import com.project.likelion13thbe.global.apiPayload.code.GeneralErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public OrderResDTO.OrderCreateResDTO createOrder(OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        Member member = memberRepository.findById(orderCreateReqDTO.getMemberId())
                .orElseThrow(() -> new CustomException(GeneralErrorCode.NOT_FOUND_404));
        Product product = productRepository.findById(orderCreateReqDTO.getProductId())
                .orElseThrow(() -> new CustomException(GeneralErrorCode.NOT_FOUND_404));

        Order order = OrderConverter.toOrder(orderCreateReqDTO, member, product);
        orderRepository.save(order);

        return OrderConverter.toOrderResDTO(order);

    }

    public void updateOrder(Long id, OrderReqDTO.OrderUpdateReqDTO dto) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new CustomException(GeneralErrorCode.NOT_FOUND_404));

        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }
        if (dto.getMemberId() != null) {
            order.setMember(memberRepository.findById(dto.getMemberId()).orElseThrow());
        }
        if (dto.getProductId() != null) {
            order.setProduct(productRepository.findById(dto.getProductId()).orElseThrow());
        }
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new CustomException(GeneralErrorCode.NOT_FOUND_404);
        }
    }

    public void updateOrderByMemberIdAndId(Long memberId, Long id, OrderReqDTO.OrderUpdateReqDTO dto) {
        Order order = orderRepository.findByIdAndMemberId(id, memberId)
                .orElseThrow(() -> new CustomException(GeneralErrorCode.UNAUTHORIZED_401));

        if (dto.getQuantity() != null) {
            order.setQuantity(dto.getQuantity());
        }
        if (dto.getStatus() != null) {
            order.setStatus(dto.getStatus());
        }
        if (dto.getMemberId() != null) {
            order.setMember(memberRepository.findById(dto.getMemberId()).orElseThrow());
        }
        if (dto.getProductId() != null) {
            order.setProduct(productRepository.findById(dto.getProductId()).orElseThrow());
        }
        orderRepository.save(order);
    }

    public void deleteOrderByMemberId(Long memberId) {
        Order order = orderRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(GeneralErrorCode.UNAUTHORIZED_401));
        orderRepository.delete(order);
    }
}
