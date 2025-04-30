package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name="Member API", description = "멤버 관련 API입니다.")
public class MemberController {

    // 카카오 로그인 OAuth 이거 어케 함?

    // 일반 로그인
    @Operation(summary = "일반 로그인")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PostMapping("/api/v1/login/normal/users/{userId}")
    public MemberResDTO.MemberResponseDTO getUser(
            @PathVariable Long userId
    ) {
        return null;
    }

    // 회원가입
    @Operation(summary = "회원가입")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PostMapping("/api/v1/register/users/{userId}")
    public MemberResDTO.MemberResponseDTO createUser(
            @PathVariable Long userId
    ) {
        return null;
    }

    // 비밀번호 수정
    @Operation(summary = "비밀번호 수정")
    @Parameter(name = "userId", description = "Member PK", example = "1")
    @PatchMapping("/api/v1/password/users/{userId}")
    public MemberResDTO.MemberResponseDTO resetPassword(
            @PathVariable Long userId
    ) {
        return null;
    }
}
