package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name="Member",description = "멤버 API")
@RequestMapping("/api/v1")
@RestController
public class MemberController {

    private final MemberCommandService memberCommandService;

    public MemberController(MemberCommandService memberCommandService) {
        this.memberCommandService = memberCommandService;
    }

    @Operation(summary = "카카오 로그인") // 카카오는 get인걸로 알고는 있는데 잘 모르겠다~
    @PostMapping("/oauth/kakao")
    public MemberResDTO.MemberResponseDTO postKakaoLogin() { return null; }


    @Operation(summary = "일반 로그인")
    @PostMapping("/login")
    public MemberResDTO.MemberResponseDTO postLogin() { return null; }


    @Operation(summary = "비밀번호 수정")
    @PatchMapping("/password-reset")
    public MemberResDTO.MemberResponseDTO patchPassword(@PathVariable long userId) { return null; }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<MemberResDTO.MemberCreateResDTO> CreateMember(
            @RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO
            ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember(memberCreateReqDTO));
    }
}
