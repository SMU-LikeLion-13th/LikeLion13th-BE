package com.project.likelion13thbe.domain.member.service.command;

import com.project.likelion13thbe.domain.member.dto.response.KakaoUserInfoResDTO;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.converter.MemberConverter;
import com.project.likelion13thbe.domain.member.dto.request.MemberReqDTO;
import com.project.likelion13thbe.domain.member.dto.response.MemberResDTO;
import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.auth.CustomUserDetails;
import com.project.likelion13thbe.global.security.jwt.JwtDTO;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    private final UserDetailsService userDetailsService;

    //private final BCryptPasswordEncoder passwordEncoder;

    public MemberResDTO.MemberCreateResDTO createMember (MemberReqDTO.MemberCreateReqDTO memberCreateReqDTO) {

        Member member = MemberConverter.toMember(memberCreateReqDTO);

        memberRepository.save(member);

        return MemberConverter.toMemberResponseDTO(member);
    }

    public JwtDTO kakaoCreateMember (KakaoUserInfoResDTO kakaoUserInfoResDTO) {
        //member 선언
        Member member;
        //db에 이메일이 없을 경우 -> 생성
        if (memberRepository.findByEmail(kakaoUserInfoResDTO.getKakaoAccount().email).isEmpty()) {
            member = MemberConverter.toKakaoMember(kakaoUserInfoResDTO);
            memberRepository.save(member);
        }
        //db에 이메일이 있을 경우
        //if와는 다른 멤버를 만들어서 아래에 인수로 전달
        else {
            //db에서 조회한 엔티티의 반환값은 Optional<>인데 이게 Member로 타입캐스팅이 안 돼서
            //그냥 Optional 객체를 만들어서 람다식으로 처리했습니다...
            Optional<Member> optionalMember = memberRepository.findByEmail(kakaoUserInfoResDTO.getKakaoAccount().email);
            member = Member.builder()
                    .email(optionalMember.map(Member::getEmail).orElse("이메일 없음"))
                    .password(optionalMember.map(Member::getPassword).orElse("비밀번호 없음"))
                    .role(optionalMember.map(Member::getRole).orElse("Role 없음"))
                    .build();
        }
        //인수에서 바로 가져오지 말고, 멤버를 만들어서 파라미터로 전달
        //멤버의 필드들을 추출해서 집어넣으면 되는 것.
        //email, password, role이 들어가야한다.
        CustomUserDetails customUserDetails = new CustomUserDetails(
                member.getEmail(),
                member.getPassword(),
                member.getRole()
        );
        //토큰 생성 후 반환
        return new JwtDTO(
                jwtUtil.createJwtAccessToken(customUserDetails),
                jwtUtil.createJwtRefreshToken(customUserDetails)
        );

    }
    //@Override
    public void updatePassword(MemberReqDTO.PasswordResetDTO passwordResetDTO) {
        Member member = memberRepository.findByMemberIdAndNotDeleted(passwordResetDTO.memberId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        //member.updatePassword(passwordEncoder.encode(passwordResetDTO.password()));

    }

    public void deleteMember(MemberReqDTO.MemberDeleteDTO memberDeleteDTO) {
        Member member = memberRepository.findByMemberIdAndNotDeleted(memberDeleteDTO.memberId()).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        member.delete();
    }


}
