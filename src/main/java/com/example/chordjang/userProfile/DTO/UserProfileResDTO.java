package com.example.chordjang.userProfile.DTO;

import com.example.chordjang.userProfile.UserProfile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResDTO {
        private int score;
        private int point;
        private int level;
        private double exp;

        public static UserProfileResDTO from(UserProfile userProfile) {
            return UserProfileResDTO.builder()
                    .score(userProfile.getScore())
                    .point(userProfile.getPoint())
                    .level(userProfile.getLevel())
                    .exp(userProfile.getExp())
                    .build();
        }
}
