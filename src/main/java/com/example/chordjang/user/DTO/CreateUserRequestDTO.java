package com.example.chordjang.user.DTO;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    String userId;
    String password;
    String email;
}
