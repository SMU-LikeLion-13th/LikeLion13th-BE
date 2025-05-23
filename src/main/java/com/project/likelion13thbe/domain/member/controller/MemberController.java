package com.project.likelion13thbe.domain.member.controller;

import com.project.likelion13thbe.domain.member.dto.request.MemberRequestDTO;
import com.project.likelion13thbe.domain.member.dto.response.KakaoUserInfoResponseDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResponseDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.service.KakaoService;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.Security.AuthErrorCode;
import com.project.likelion13thbe.global.Security.AuthException;
import com.project.likelion13thbe.global.Security.AuthService;
import com.project.likelion13thbe.global.Security.DTO.JwtDto;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;
    private final KakaoService kakaoService;
    private final AuthService authService;

    @Operation(summary = "카카오 로그인", description = "카카오 로그인을 수행")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "카카오 로그인 성공")
    })
    @GetMapping("/callback/kakao")
    public CustomResponse<JwtDto> callbackKakaoLogin(@RequestParam("code") String code){
        // 1. 카카오 인증서버에서 토큰을 발급받는다.
        // 인가code와 Redirect URL을 파라미터로 전달하여 카카오 인증서버에 요청.
        String accessToken = kakaoService.getAccessTokenFromKakao(code);

        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자의 정보 요청.
        KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 회원가입 & 로그인 처리
        // 여기에 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
        // 이메일이 있으면 로그인 없으면 회원가입 시키기
        String emailInfo = userInfo.getKakaoAccount().getEmail(); // 카카오로 부터 넘겨받은 정보에서 사용자 email 정보 얻기
        Optional<Member> existingMember = memberQueryService.findByEmail(emailInfo); // DB에서 해당 email 가져오기

        // 존재 여부에 따른 행동
        Member member;
        if (existingMember.isPresent()) {
            member = existingMember.get();
        } else {
            MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO =
                    MemberRequestDTO.MemberCreateRequestDTO.builder()
                            .email(userInfo.getKakaoAccount().getEmail())
                            .password(UUID.randomUUID().toString()) // 소셜 전용 더미 비밀번호
                            .name(userInfo.getKakaoAccount().profile.nickName)
                            .build();

            MemberResponseDTO.MemberCreateResDTO createdDto = memberCommandService.createMember(memberCreateRequestDTO);
            member = memberQueryService.findByEmail(memberCreateRequestDTO.email()).get();
        }

        JwtDto jwt = authService.createJwt(member);
        return CustomResponse.onSuccess(jwt);
    }

    @Operation(summary = "일반 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtDto.class)))    })
    @PostMapping("/login")
    public ResponseEntity<JwtDto> localLogin(@RequestBody MemberRequestDTO.LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Operation(summary = "비밀번호 수정", description = "비밀번호를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 수정 성공")
    })
    @PostMapping("/password-reset")
    public CustomResponse<String> resetPassword(
            @RequestBody MemberRequestDTO.PasswordResetDTO requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long userId = Long.parseLong(userDetails.getUsername());
        memberCommandService.updatePassword(userId, requestDTO);
        return CustomResponse.onSuccess("비밀번호 변경 성공");
    }

    @Operation(summary = "회원가입", description = "사용자 회원가입")
    @ApiResponse(responseCode = "201",
            description = "회원 생성 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @PostMapping("/auth")
    public ResponseEntity<MemberResponseDTO.MemberCreateResDTO> createMember(
            @RequestBody MemberRequestDTO.MemberCreateRequestDTO memberCreateRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberCommandService.createMember((memberCreateRequestDTO)));
    }

    @Operation(summary = "내 정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    @ApiResponse(responseCode = "200",
            description = "사용자 정보 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberPreviewResDTO.class)
            )
    )
    @GetMapping("/me")
    public ResponseEntity<MemberResponseDTO.MemberPreviewResDTO> getMyInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long userId = Long.parseLong(userDetails.getUsername());
        return ResponseEntity.ok(memberQueryService.getMember(userId));
    }


    @Operation(summary = "4주차 실습", description = "사용자 정보 페이지네이션 조회_offset 기반")
    @ApiResponse(responseCode = "200",
            description = "사용자 정보 조회 성공",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MemberResponseDTO.MemberCreateResponseDTO.class)
            )
    )
    @GetMapping("/offset")
    public CustomResponse<MemberResponseDTO.MemberOffsetResDTO> getMemberOffset(
            @RequestParam Integer offset,
            @RequestParam Integer size
    ) {
        return CustomResponse.onSuccess(memberQueryService.getMemberOffset(offset, size));
    }

    @DeleteMapping("/members/{memberId}")
    @Operation(summary = "회원 탈퇴", description = "회원 계정 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공")
    })
    public CustomResponse<String> deleteMember(
            @PathVariable Long memberId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long userId = Long.parseLong(userDetails.getUsername());
        if (!userId.equals(memberId)) {
            throw new AuthException(AuthErrorCode._FORBIDDEN);
        }
        memberCommandService.deleteMember(memberId);
        return CustomResponse.onSuccess("회원 탈퇴 성공");
    }
}
