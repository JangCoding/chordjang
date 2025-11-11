package com.example.chordjang.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {   // URL 접근 권한 설정. Filter 설정. 암호화 설정.

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                // CSRF 보호 비활성화 (JWT 사용 시 일반적으로 비활성화)
                .cors(Customizer.withDefaults())   // CORS 설정 추가
                .csrf(csrf -> csrf.disable())

                // 세션을 사용하지 않으므로, STATELESS로 설정
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // URL별 권한 관리 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll() // 인증 없이 접근 허용

                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )

                // H2 콘솔을 위한 헤더 설정 (개발 환경에서만 필요)
                .headers(headers ->
                        headers.frameOptions(frameOptions -> frameOptions.sameOrigin())
                )


                // 우리가 만든 JwtAuthenticationFilter 를 UsernamePasswordAuthenticationFilter 앞에 추가
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 암호화를 위한 BCryptPasswordEncoder 등록
        return new BCryptPasswordEncoder();
    }
}