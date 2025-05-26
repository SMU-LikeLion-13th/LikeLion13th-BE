package com.project.likelion13thbe.global.kakao;


import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.entity.IsTempPassword;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.entity.Role;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.CustomUserDetails;
import com.project.likelion13thbe.global.security.JwtUtil;
import com.project.likelion13thbe.global.security.dto.JwtDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final MemberRepository memberRepository;
    private final MemberCommandService memberCommandService;
    private final JwtUtil jwtUtil;

    @GetMapping("/callback/kakao")
    public CustomResponse<JwtDTO.JwtResDTO> callback(@RequestParam("code") String code) {

        // 1. 카카오 인증서버에서 토큰을 발급받는다.
        // 인가code와 Redirect URL을 파라미터로 전달하여 카카오 인증서버에 요청.
        String accessToken = kakaoService.getAccessTokenFromKakao(code);


        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자 정보 요청.
        KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 회원가입 & 로그인 처리
        // 여기에 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
        Optional<Member> member = memberRepository.findByEmail(String.valueOf(userInfo.getKakaoAccount().getEmail()));

        String kakaoName = userInfo.getKakaoAccount().getProfile().getNickName();
        String kakaoEmail = String.valueOf(userInfo.getKakaoAccount().getEmail());

        if (member.isPresent()) {
            CustomUserDetails customUserDetails = new CustomUserDetails(kakaoEmail, null, Role.ROLE_USER, IsTempPassword.NORMAL);
            String Token = jwtUtil.createJwtAccessToken(customUserDetails);
            String refreshToken = jwtUtil.createJwtRefreshToken(customUserDetails);

            // CustomResponse 사용하여 응답 통일
            JwtDTO.JwtResDTO jwtResDTO = JwtDTO.JwtResDTO.builder()
                    .accessToken(Token)
                    .refreshToken(refreshToken)
                    .build();
            return CustomResponse.onSuccess(jwtResDTO);
        }


        MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO = MemberConverter.toMemberKakaoRequestDTO(kakaoName, kakaoEmail);
        memberCommandService.createMember(memberCreateReqDTO);

        return CustomResponse.onSuccess(null);
    }
}
