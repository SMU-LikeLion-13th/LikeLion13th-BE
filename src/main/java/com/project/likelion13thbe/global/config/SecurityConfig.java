package com.project.likelion13thbe.global.config;

import com.project.likelion13thbe.domain.member.repository.MemberRepository;
import com.project.likelion13thbe.global.security.filter.JwtAuthorizationFilter;
import com.project.likelion13thbe.global.security.handler.CustomLogoutHandler;
import com.project.likelion13thbe.global.security.handler.JwtAccessDeniedHandler;
import com.project.likelion13thbe.global.security.handler.JwtAuthenticationEntryPoint;
import com.project.likelion13thbe.global.security.filter.CustomLoginFilter;
import com.project.likelion13thbe.global.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // 인가에 실패한 경우 실행할 예외 처리 handler
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 인증에 실패한 경우 실행할 예외 처리 handler
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    // 로그아웃 handler 주입
    private final CustomLogoutHandler customLogoutHandler;

    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;

    // AuthenticationManager가 인자로 받을 AuthenticationConfiguration 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;

    // AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, memberRepository);
    }

    // 비밀번호를 해시로 암호화 및 검증하는 BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 허용할 URL을 배열 형태로 관리
    private final String[] allowUrl = {
            "/",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/api/v1/members/login",
            "/api/v1/members/signup",
            "/callback/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CustomLoginFilter loginFilter = new CustomLoginFilter(
                authenticationManager(authenticationConfiguration), jwtUtil
        );
        loginFilter.setFilterProcessesUrl("/api/v1/members/login");

        http
                .authorizeHttpRequests(request -> request
                        // allowUrl은 모두 허용
                        .requestMatchers(allowUrl).permitAll()
                        // 이외의 요청에 대해서는 인증 필요
                        .anyRequest().authenticated())
                // jwtFilter를 UsernamePasswordAuthenticationFilter 앞에 오도록 설정
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                // form Login 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화
                .httpBasic(HttpBasicConfigurer::disable)
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 인증 인가에 대한 예외처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))

                .logout(logout -> logout
                        .logoutUrl("/api/v1/members/logout")
                        .addLogoutHandler(customLogoutHandler)
                        .logoutSuccessHandler((request, response, authentication) ->
                                response.setStatus(HttpServletResponse.SC_OK)));

        // build()를 통해 SecurityFilterChain 형태로 반환
        return http.build();
    }
}
