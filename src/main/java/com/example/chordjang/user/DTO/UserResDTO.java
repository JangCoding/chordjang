package com.example.chordjang.user.DTO;

import com.example.chordjang.user.RoleEnum;
import com.example.chordjang.user.User;
import com.example.chordjang.userProfile.DTO.UserProfileResDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResDTO {
    private final Long id;
    private final String loginId;
    private final String email;
    private final String nickName;
    private final RoleEnum role;
    private final UserProfileResDTO userProfile;

    public static UserResDTO fromEntity(User user){
        return UserResDTO.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .email(user.getEmail())
                .nickName(user.getNickName())
                .role(user.getRole())
                .userProfile(UserProfileResDTO.fromEntity(user.getUserProfile()))
                .build();
    }
}
