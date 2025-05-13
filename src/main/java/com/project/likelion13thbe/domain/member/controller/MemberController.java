package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandServiceImpl;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryServiceImpl;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Member", description="회원/인증 API")
public class MemberController {

    private final MemberCommandServiceImpl memberCommandServiceImpl;
    private final MemberQueryServiceImpl memberQueryServiceImpl;

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
    public CustomResponse<MemberResDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberReqDTO.SignUpRequest signUpRequest) {
        return CustomResponse.onSuccess(HttpStatus.CREATED, memberCommandServiceImpl.createMember(signUpRequest));

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
    @PatchMapping("/users/{userId}/password")
    public CustomResponse<String> resetPassword(
            @PathVariable Long userId,
            @RequestBody MemberReqDTO.ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

    @GetMapping
    public ResponseEntity<MemberResDTO.MemberPreviewResDTO> getMember() {
        return ResponseEntity.ok(memberQueryServiceImpl.getMember());
    }
    @GetMapping("/offset")
    public ResponseEntity<MemberResDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset, @RequestParam Integer size) {
        return ResponseEntity.ok(memberQueryServiceImpl.getMemberOffset(offset, size));
    }

    @GetMapping("/cursor")
    public ResponseEntity<MemberResDTO.MemberCursorResDTO> getMemberCursor(
            @RequestParam Long cursor, @RequestParam Integer size
    ) {
        return ResponseEntity.ok(memberQueryServiceImpl.getMemberCursor(cursor, size));
    }
    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원 탈퇴",description = "회원 계정을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description ="회원 탈퇴 성공" )
    })
    public CustomResponse<String> deleteMember(@PathVariable String memberId ) {
        memberCommandServiceImpl.deleteMember(memberId);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }



}
