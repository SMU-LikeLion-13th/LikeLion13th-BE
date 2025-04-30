package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    @Operation(summary = "카카오 로그인", description = "카카오 로그인을 수행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카카오 로그인 성공")
    })
    @PostMapping("/api/v1/login/kakao")
    public void kakaoLogin() {
        // 로그인 로직
    }

    @Operation(summary = "일반 로그인", description = "일반 로그인을 수행")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "일반 로그인 성공")
    })
    @PostMapping("/api/v1/login")
    public void login() {
        // 로그인 로직
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    })
    @PostMapping("/api/v1/password-reset")
    public void resetPassword() {
        // 비밀번호 수정 로직
    }

    @Operation(summary = "회원가입", description = "신규 회원을 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공")
    })
    @PostMapping("/api/v1/users")
    public void signup(@RequestBody MemberRequestDTO.MemberCreateRequestDTO requestDTO) {
        // 회원가입 로직
    }
}
