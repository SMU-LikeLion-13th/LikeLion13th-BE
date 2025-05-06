package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.domain.review.dto.response.ReviewResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("/members")
@Tag(name = "멤버관련", description = "멤버 관련 API")
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;



    @GetMapping
    public ResponseEntity<MemberResDTO.MemberPreviewResDTO> getMember() {
        return ResponseEntity.ok(memberQueryService.getMember());
    }

    public ResponseEntity<MemberResDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return ResponseEntity.ok(memberQueryService.getMemberOffset(offset, size));
    }

    //카카오 로그인
    @Operation(summary = "카카오 로그인 API", description = "카카오 로그인 관련 API")
    @PostMapping("/api/v1/auth/kakao")
    public MemberResDTO.MemberResponseDTO kakaoLogin() { return null; } //request는 구현하지 않았음.

    //일반 로그인
    @Operation(summary = "일반 로그인 API", description = "일반 로그인 API입니다.")
    @PostMapping("/api/v1/auth/login")
    public MemberResDTO.MemberResponseDTO login() { return null; } //request는 구현하지 않았음.
    //비밀번호 수정
    @Operation(summary = "비밀번호 수정 API", description = "비밀번호 수정 API입니다.")
    @PatchMapping("/api/v1/users")
    public MemberResDTO.MemberResponseDTO patchMember(@PathVariable long userId) { return null; }
    //회원가입
    @Operation(summary = "회원가입 API", description = "회원가입 API입니다.")
    @PostMapping("/api/v1/users")
    public ResponseEntity<MemberResDTO.MemberCreateResDTO> createMember
    (@RequestBody MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember(memberCreateReqDTO));
    }

}
