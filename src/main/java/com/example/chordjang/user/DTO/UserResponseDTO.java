package com.example.chordjang.user.DTO;

import com.example.chordjang.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    private final String userId;
    private final String email;

    public static UserResponseDTO fromEntity(User user){
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .build();
    }
}
