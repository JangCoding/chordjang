package com.example.chordjang.userProfile;

import com.example.chordjang.userProfile.DTO.UpdateUserProfileReqDTO;
import com.example.chordjang.userProfile.DTO.UserProfileResDTO;

public interface UserProfileService {
        UserProfileResDTO getUserProfile(Long userId);
        UserProfileResDTO updateUserProfile(Long userId, UpdateUserProfileReqDTO req);
}
