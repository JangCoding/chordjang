package com.example.chordjang.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponseDTO {
    private String accessToken;
}
