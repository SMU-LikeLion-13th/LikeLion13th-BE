package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="Member", description="회원/인증 API")
public class MemberController {

    @Operation(description = "일반 로그인")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResDTO.JwtTokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/users/login")
    public ResponseEntity<MemberResDTO.JwtTokenResponse> login(@RequestBody MemberReqDTO.LoginRequest loginRequest) {
        return null;
    }

    @Operation(description = "카카오 로그인")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResDTO.JwtTokenResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/users/login/kakao")
    public ResponseEntity<MemberResDTO.JwtTokenResponse> kakaoLogin(@RequestBody MemberReqDTO.KakaoLoginRequest kakaologinRequest) {
        return null;
    }

    @Operation(description = "회원가입")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "401", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/users")
    public ResponseEntity<Void> signUp(@RequestBody MemberReqDTO.SignUpRequest signUpRequest) {
        return null;
    }

    @Operation(description = "비밀번호 수정")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "ok, 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(
                    responseCode = "401", description = "실패",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping("/users/password")
    public ResponseEntity<Void> localLogin(@RequestBody MemberReqDTO.ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

}
