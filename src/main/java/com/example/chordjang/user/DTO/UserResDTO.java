package com.example.chordjang.user.DTO;

import com.example.chordjang.user.RoleEnum;
import com.example.chordjang.user.User;
import com.example.chordjang.userProfile.DTO.UserProfileResDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDTO {
    private final String userId;
    private final String email;
    private final RoleEnum role;
    private final UserProfileResDTO userProfile;

    public static UserResDTO fromEntity(User user){
        return UserResDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .role(user.getRole())
                .userProfile(UserProfileResDTO.fromEntity(user.getUserProfile()))
                .build();
    }
}
