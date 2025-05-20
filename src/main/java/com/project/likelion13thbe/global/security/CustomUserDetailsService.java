package com.project.likelion13thbe.global.security;

import com.project.likelion13thbe.domain.member.entity.Member;
import com.project.likelion13thbe.domain.member.repository.MemberRepository;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> userEntity = memberRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            Member member = userEntity.get();
            return new CustomUserDetails(member.getEmail(), member.getPassword(), member.getRole());
        }
        throw new UsernameNotFoundException("사용자가 존재하지 않음.");
    }
}
