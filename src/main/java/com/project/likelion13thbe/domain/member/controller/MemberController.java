package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Member", description = "유저 관련 API")
public class MemberController {

    @Operation(summary = "일반 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MemberResponseDTO.JwtTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized 아이디나 비밀번호 오류",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/login")
    public ResponseEntity<MemberResponseDTO.JwtTokenResponse> localLogin(@RequestBody MemberRequestDTO.LoginRequestDTO loginRequestDTO) {
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

    @Operation(summary = "내 리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponseDTO.ReviewListResponseDTO.class))),})
            @GetMapping("/api/v1/my/reviews")
    public ResponseEntity<ReviewResponseDTO.ReviewListResponseDTO> getMyReviews() {
        return null;
    }

    @Operation(summary = "카카오 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.JwtTokenResponse.class))),
            @ApiResponse(responseCode = "400", description = "BadRequest",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "카카오 토큰 발급 실패",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/api/v1/kakao/login")
    public ResponseEntity<MemberResponseDTO.JwtTokenResponse> kakaoLogin(@RequestBody MemberRequestDTO.kakaoLoginRequestDTO kakaoLoginRequestDTO) {
        return null;
    }
}
