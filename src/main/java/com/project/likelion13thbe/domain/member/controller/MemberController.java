package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
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

    private final MemberCommandServiceImpl memberCommandService;

    @Operation(summary = "4주차 실습", description = "사용자 회원가입")
    @ApiResponse(   responseCode = "201",
            description = "회원 생성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @PostMapping
    public ResponseEntity<MemberResponseDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember((memberCreateRequestDTO)));
    }
}
