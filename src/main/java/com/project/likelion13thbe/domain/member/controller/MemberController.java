package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Tag(name="Member", description = "Member 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    @Operation(description = "회원가입")
    @PostMapping
    public ResponseEntity<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember(memberCreateReqDTO));
    }
    @GetMapping

    @Operation(description = "비밀번호 수정")
    @PatchMapping("/users/reset-password")
    public ResponseEntity<MemberResDTO.ResetPasswordReqDTO> updatePassword(
            @RequestBody MemberReqDTO.ResetPasswordReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> login(
            @RequestBody MemberReqDTO.LoginReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "카카오 로그인")
    @PostMapping("/login/kakao")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> kakaoLogin(
            @RequestBody MemberReqDTO.KakaoLoginRequestDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
}
