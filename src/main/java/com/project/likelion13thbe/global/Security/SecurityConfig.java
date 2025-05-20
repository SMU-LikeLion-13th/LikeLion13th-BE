package com.project.likelion13thbe.global.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // 빈 등록
@EnableWebSecurity // 필터 체인 관리 시작 어노테이션
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    // 인가 실패 핸들러
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    // 인증 실패 핸들러
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;


    //인증이 필요하지 않은 url -> 허용할 URL들
    private final String[] allowUrl = {
            "/api/v1/login", //로그인 은 인증이 필요하지 않음
            "/api/v1/auth", // 회원가입은 인증이 필요하지 않음
            "/api/v1/login/kakao",
            "/auth/reissue", // 토큰 재발급은 인증이 필요하지 않음
            "/auth/**",
            "api/usage",
            "/swagger-ui/**",   // swagger 관련 URL
            "/v3/api-docs/**",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        CustomLoginFilter loginFilter = new CustomLoginFilter(authenticationManager(authenticationConfiguration), jwtUtil);
        loginFilter.setFilterProcessesUrl("/api/v1/login");

        http
                // 허용할 URL, 역할별로 나눌 URL, 인증을 요구하는 URL 설절
                .authorizeHttpRequests(request -> request
                        // allowUrl 모두 허용
                        .requestMatchers(allowUrl).permitAll()
                        // 이외의 요청에 대해서 인증이 필요하도록 설정
                        .anyRequest().authenticated())
                // jwtFilter를 UsernaemPasswordAuthenticationFilter 앞에 오도록 설절
                .addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class)
                // formLogic 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // httpBasic 비활성화
                .httpBasic(HttpBasicConfigurer::disable)
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // 인증 인가에 대한 예외처리
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // 인가에 대해 예외처리할 핸들러 추가
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        // 인증에 대해 예외처리할 핸들러 추가
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
        ;
        // build 해서 시큐리티필터체인 현태로 반환
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}

