package com.project.likelion13thbe.global.security.service;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.exception.MemberErrorCode;
import com.project.likelion13thbe.domain.member.exception.MemberException;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.entity.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // username(email)로 CustomUserDetail 가져오기
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        log.info("[ CustomUserDetailsService ] Email을 이용하여 User를 검색합니다.");
//        Optional<Member> userEntity = memberRepository.findByEmailAndNotDeleted(email);
//        if (userEntity.isPresent()) {
//            Member member = userEntity.get();
//            return new CustomUserDetails(member.getEmail(), member.getPassword(), member.getRole());
//        }
//        throw new UsernameNotFoundException("Member Not Found");


        Member member = memberRepository.findByEmailAndNotDeleted(email)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        log.info("Member Role: {}", member.getRole());

        return new CustomUserDetails(member);
    }
}
