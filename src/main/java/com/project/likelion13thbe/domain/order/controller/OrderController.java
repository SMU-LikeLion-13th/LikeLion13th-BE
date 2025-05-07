package com.project.likelion13thbe.domain.order.controller;

import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.service.command.OrderCommandService;
import com.project.likelion13thbe.domain.order.service.query.OrderQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "주문 관련", description = "주문 관련 API")
public class OrderController {

    private final OrderCommandService orderCommandService;
    private final MemberQueryService memberQueryService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public ResponseEntity<OrderResDTO.OrderCreateResDTO> createOrder(
            @RequestBody OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderCommandService.createOrder(orderCreateReqDTO));
    }

    @GetMapping
    public ResponseEntity<OrderResDTO.OrderPreviewResDTO> getOrder() {
        return ResponseEntity.ok(orderQueryService.getOrder());
    }
}
