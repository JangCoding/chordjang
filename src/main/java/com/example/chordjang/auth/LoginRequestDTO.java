package com.example.chordjang.auth;

import lombok.Getter;

@Getter
public class LoginRequestDTO {
    private String userId;
    private String password;
}
