package com.project.likelion13thbe.domain.order.controller;

import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.service.command.OrderCommandService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequiredArgsConstructor
@Tag(name="Order API", description = "주문 관련 API입니다.")
public class OrderController {
    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<OrderResDTO.OrderCreateResDTO> createOrder(
            @RequestBody OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatusCode.CREATED)
                .body(orderCommandService.createOrder(OrderReqDTO.orderCreateReqDTO));
    }

    @PatchMapping("/order/{orderId}")
    @Operation(summary = "배송 상태 변경")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "배송상태 변경 성공")
    })
    public CustomResponse<String> updateOrder(
            @RequestBody OrderReqDTO.updateOrderStatusDTO request
    ) {
        orderCommandService.updateOrderStatus(request);
        return CustomResponse.onSuccess("배송상태 변경 성공");
    }
}