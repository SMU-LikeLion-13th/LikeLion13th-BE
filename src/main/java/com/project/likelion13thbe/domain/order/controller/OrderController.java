package com.project.likelion13thbe.domain.order.controller;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.order.dto.request.OrderReqDTO;
import com.project.likelion13thbe.domain.order.dto.response.OrderResDTO;
import com.project.likelion13thbe.domain.order.service.command.OrderCommandService;
import com.project.likelion13thbe.global.apiPayload.code.GeneralErrorCode;
import com.project.likelion13thbe.global.apiPayload.exception.CustomException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name="Order", description = "Order 관련 API입니다.")
public class OrderController {
    private final OrderCommandService orderCommandService;
    private final MemberCommandService memberCommandService;
    private final MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<OrderResDTO.OrderCreateResDTO> createOrder(
            @RequestBody OrderReqDTO.OrderCreateReqDTO orderCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderCommandService.createOrder(orderCreateReqDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderReqDTO.OrderUpdateReqDTO orderUpdateReqDTO
    ) {
        orderCommandService.updateOrder(id, orderUpdateReqDTO);
        return ResponseEntity.ok("Order Updated Successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderCommandService.deleteOrder(id);
        return ResponseEntity.ok("Order Deleted Successfully.");
    }

    // Update @AuthenticationPrincipal
    @PatchMapping("/myaccount/{id}")
    public ResponseEntity<String> updateMyOrder(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long id,
            @RequestBody OrderReqDTO.OrderUpdateReqDTO dto
    ) {
        String username = userDetails.getUsername();
        Member member = memberRepository.findByEmail(username)
                        .orElseThrow(() -> new CustomException(GeneralErrorCode.UNAUTHORIZED_401));
        Long memberId = member.getId();


        orderCommandService.updateOrderByMemberIdAndId(memberId, id, dto);
        return ResponseEntity.ok("Order Updated Successfully.");
    }

    // Delete @AuthenticationPrincipal
    @DeleteMapping("/myaccount")
    public ResponseEntity<String> deleteMyOrder(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername();

        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new CustomException(GeneralErrorCode.UNAUTHORIZED_401));
        Long memberId = member.getId();

        orderCommandService.deleteOrderByMemberId(memberId);
        return ResponseEntity.ok("Member Deleted Successfully.");
    }
}
