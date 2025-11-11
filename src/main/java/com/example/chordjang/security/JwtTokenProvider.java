package com.example.chordjang.security;

import com.example.chordjang.user.RoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;

    // 토큰 만료 시간 (30분)
    private final long TOKEN_VALIDITY_IN_MILLI_SEC = 30 * 60 * 1000L;

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 받아와 JWT 토큰 BUILD.
    public String createToken(String userId, RoleEnum role) {
        Date now = new Date();  // 현재 시간
        Date validity = new Date(now.getTime() + TOKEN_VALIDITY_IN_MILLI_SEC);  // 토큰 만료 시간

        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role.name())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {

        //JWT 안에 들어있는 정보(데이터) 덩어리
        // header.payload.signature 의 payload 부분. 유저 정보들이 key-value 로 저장되어 있음.
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)             // 토큰 서명 검증에 사용될 비밀키 설정
                .build()
                .parseClaimsJws(token)          // JWT parsing + 서명 검증
                .getBody();                     // payload 부분(Claims) 꺼냄

        // subject = sub = 보통 userName
        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());

        // Security 인증 객체 생성. principal, credentials(비밀번호), authorities(권한, role)
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            // parseClaimsJws() : 서명 + 유효성 + 만료 전부 검증. 다양한 예외던짐.
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.info("Invalid JWT token: {}", e.getMessage());
        }
        return false;
    }

    //Authentication(HTTP 표준 헤더. 인증 방식(Bearer or ETC) + 인증 값) 에서 "bearer " 떼고 토큰에 저장하기 위해
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}