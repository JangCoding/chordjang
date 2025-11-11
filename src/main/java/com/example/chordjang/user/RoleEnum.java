package com.example.chordjang.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
// Spring Security 사용자가 가지고 있는 권한을 표현하는 가장 기본적인 인터페이스.
// RoleEnum 이 Security 에서 권한으로 사용될 수 있게 함
public enum RoleEnum implements GrantedAuthority {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String key;

    @Override
    public String getAuthority() {
        return this.getKey();
    }
}