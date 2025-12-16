package com.example.chordjang.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {
    //TODO Auth 로 id 가져오기, 권한 확인
//    public static Long getCurrUserId() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        if (auth == null || !auth.isAuthenticated() || auth.getPrincipal() == "anonymousUser") {
//            throw new UnauthorizedException("로그인이 필요합니다.");
//        }
//
//        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
//        return userDetails.getId();
//    }
}
