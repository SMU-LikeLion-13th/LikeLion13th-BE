package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.review.dto.request.ReviewReqDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="Member", description = "Member 관련 API")
public class MemberController {
    @Operation(description = "비밀번호 수정")
    @PatchMapping("/api/v1/users/reset-password")
    public ResponseEntity<MemberResDTO.ResetPasswordReqDTO> updatePassword(
            @RequestBody MemberReqDTO.ResetPasswordReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "로그인")
    @PatchMapping("/api/v1/login")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> login(
            @RequestBody MemberReqDTO.LoginReqDTO dto
    ) {
        return ResponseEntity.ok(null);
    }

    @Operation(description = "카카오 로그인")
    @PatchMapping("/api/v1/login/kakao")
    public ResponseEntity<MemberResDTO.LoginJwtTokenResDTo> kakaoLogin(
            @RequestBody MemberReqDTO.KakaoLoginRequestDTO dto
    ) {
        return ResponseEntity.ok(null);
    }
}
