package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Member", description = "Member 관련 API")
public class MemberController {
    @Operation(description = "회원가입")
    @PostMapping("/api/v1/signup")
    public ResponseEntity<MemberResDTO.SignupResDTO> signup(
            @RequestBody MemberReqDTO.SignupReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "비밀번호 수정")
    @PatchMapping("/api/v1/users/reset-password")
    public ResponseEntity<MemberResDTO.ResetPasswordReqDTO> updatePassword(
            @RequestBody MemberReqDTO.ResetPasswordReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "로그인")
    @PostMapping("/api/v1/login")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> login(
            @RequestBody MemberReqDTO.LoginReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "카카오 로그인")
    @PostMapping("/api/v1/login/kakao")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> kakaoLogin(
            @RequestBody MemberReqDTO.KakaoLoginRequestDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
}
