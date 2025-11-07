package com.example.chordjang.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);  // Bearer 떼서
        if (token != null && jwtTokenProvider.validateToken(token)) {   // 검증하고
            Authentication authentication = jwtTokenProvider.getAuthentication(token);  // 인증정보 꺼내서 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);   // SecurityContextHolder(현재 요청 인증 정보 저장소. 지갑) 에 인증값(신분증) 저장
        }
        filterChain.doFilter(request, response);
    }
}