package com.project.likelion13thbe.global.security.customUserDetails;

import com.project.likelion13thbe.domain.member.entity.MemberStatus;
import com.project.likelion13thbe.domain.member.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final Role roles;
    private final MemberStatus memberStatus;

    public CustomUserDetails(String email, String password, Role roles, MemberStatus memberStatus) {
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.memberStatus = memberStatus;
    }

    // 해당 User 의 권한을 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roles.name()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getMemberStatus() {
        return memberStatus.name();
    }

    // Account 가 만료되었는지?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Account 가 잠겨있는지?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Credential 만료되지 않았는지?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 활성화가 되어있는지?
    @Override
    public boolean isEnabled() {
        // User Entity 에서 Status 가져온 후 true? false? 검사
        return true;
    }

    // 무조건 지금 비밀번호를 변경해야 하는 상황인지?
    public boolean isPasswordChangeRequired() {
        return this.memberStatus == MemberStatus.MUST_CHANGE_PASSWORD;
    }
}