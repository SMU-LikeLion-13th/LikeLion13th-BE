package com.project.likelion13thbe.global.kakao;

import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.entity.Role;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.domain.member.service.command.MemberCommandService;
import com.project.likelion13thbe.domain.member.service.query.MemberQueryService;
import com.project.likelion13thbe.global.apiPayload.CustomResponse;
import com.project.likelion13thbe.global.security.customUserDetails.CustomUserDetails;
import com.project.likelion13thbe.global.security.dto.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberQueryService memberQueryService;
    private final JwtUtil jwtUtil;

    @GetMapping("/callback/kakao")
    public CustomResponse<JwtDTO> callback(
            @RequestParam("code") String code

    ) {

        // 1. 카카오 인증서버에서 토큰을 발급받는다.
        // 인가code와 Redirect URL을 파라미터로 전달하여 카카오 인증서버에 요청.
        String accessToken = kakaoService.getAccessTokenFromKakao(code);


        // 2. 1번에서 받은 토큰으로 카카오 리소스 서버에 사용자 정보 요청.
        KakaoUserInfoResDTO userInfo = kakaoService.getUserInfo(accessToken);

        // 3. 회원가입 & 로그인 처리
        // 여기에 서버 사용자 로그인(인증) 또는 회원가입 로직 추가
        Optional<Member> member = memberRepository.findByEmailAndNotDeleted(userInfo.kakaoAccount().email());

        log.info("email = {}", userInfo.kakaoAccount().email());

        if (member.isEmpty()) {
            // 존재하지 않으니 회원가입으로 이동
            log.info("[ KakaoLoginController ] 회원이 아닙니다. 회원 가입을 먼저 진행합니다");
            MemberReqDTO.MemberCreateReqDTO memberKakaoCreateReqDTO =
                    MemberConverter.toMemberKakaoCreateReqDTO(userInfo);

            memberCommandService.createMember(memberKakaoCreateReqDTO);
        }
        // 로그인으로 이동
        log.info("[ KakaoLoginController ] 회원 정보가 존재해 로그인을 진행합니다");
        CustomUserDetails customUserDetails = new CustomUserDetails(userInfo.kakaoAccount().email(), null, Role.USER);

        //Client 에게 줄 Response 를 Build
        JwtDTO jwtDto = JwtDTO.builder()
                .accessToken(jwtUtil.createJwtAccessToken(customUserDetails)) //access token 생성
                .refreshToken(jwtUtil.createJwtRefreshToken(customUserDetails)) //refresh token 생성
                .build();

        // CustomResponse 사용하여 응답 통일
        return CustomResponse.onSuccess(jwtDto);
    }
}

