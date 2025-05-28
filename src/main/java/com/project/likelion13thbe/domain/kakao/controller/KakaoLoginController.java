package com.project.likelion13thbe.domain.kakao.controller;

import com.project.likelion13thbe.domain.kakao.dto.response.KakaoUserInfoResponseDTO;
import com.project.likelion13thbe.domain.kakao.service.KakaoService;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.type.Role;
import com.project.likelion13thbe.global.apiPayload.exception.CustomResponse;
import com.project.likelion13thbe.global.security.CustomUserDetails;
import com.project.likelion13thbe.global.security.CustomUserDetailsService;
import com.project.likelion13thbe.global.security.dto.JwtDTO;
import com.project.likelion13thbe.global.security.filter.CustomLoginFilter;
import com.project.likelion13thbe.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.StringTokenizer;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;


    @GetMapping("/callback/kakao")
    public CustomResponse<JwtDTO> callback(@RequestParam("code") String code) {

        // 1. 카카오 인증서버에서 토큰을 발급받는다.
        // 인가code와 Redirect URL을 파라미터로 전달하여 카카오 인증서버에 요청.
        String accessToken = kakaoService.getAccessTokenFromKakao(code);


        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자 정보 요청.
        KakaoUserInfoResponseDTO userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 회원가입 & 로그인 처리
        // 여기에 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
        Optional<Member> exsitMember = memberRepository.findByEmail(userInfo.getKakaoAccount().getEmail());
        Member member;
        if (exsitMember.isPresent()) {
            member = exsitMember.get();
        }
        else{
            member = Member.builder()
                    .email(userInfo.getKakaoAccount().getEmail())
                    .image(userInfo.getKakaoAccount().getProfile().getProfileImageUrl())
                    .name(userInfo.getKakaoAccount().getName())
                    .role(Role.USER)
                    .build();
            memberRepository.save(member);
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(member);


        String accessjwt = jwtUtil.createJwtAccessToken(customUserDetails);
        String refreshjwt = jwtUtil.createJwtRefreshToken(customUserDetails);

        JwtDTO jwtDTO = JwtDTO.builder()
                .accessToken(accessjwt)
                .refreshToken(refreshjwt)
                .build();

        return CustomResponse.onSuccess(jwtDTO);
    }
}

