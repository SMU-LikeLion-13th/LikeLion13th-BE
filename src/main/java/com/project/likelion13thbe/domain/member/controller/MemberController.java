package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Member", description = "유저 관련 API")
public class MemberController {

    @Operation(summary = "일반 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized\t\n 1. 가입되지 않은 회원 \t\n 2. 아이디나 비밀번호 오류",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/login")
    public ResponseEntity<?> localLogin(@RequestBody MemberRequestDTO.LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Operation(summary = "비밀번호 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized\t\n 1. jwt 유효하지 않음 \t\n 2. 비밀번호 유형 맞지 않음",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/password-reset")
    public ResponseEntity<?> resetPassword(@RequestBody MemberRequestDTO.ResetPasswordRequestDTO resetPasswordRequestDTO) {
        return null;
    }

    @Operation(summary = "회원가입")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Conflict, 중복된 이메일",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/signup")
    public ResponseEntity<?> localSignUp(@RequestBody MemberRequestDTO.SignUpRequestDTO signUpRequestDTO) {
        return null;
    }
}
