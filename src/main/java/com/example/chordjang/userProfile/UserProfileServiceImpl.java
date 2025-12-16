package com.example.chordjang.userProfile;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.userProfile.DTO.UpdateUserProfileReqDTO;
import com.example.chordjang.userProfile.DTO.UserProfileResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Override
    public UserProfileResDTO getUserProfile(String userId) {
        UserProfile userProfile = userProfileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "UserId", userId));

        return UserProfileResDTO.from(userProfile);
    }

    @Override
    public UserProfileResDTO updateUserProfile(String userId, UpdateUserProfileReqDTO req) {
        UserProfile userProfile = userProfileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "UserId", userId));

        userProfile.updatePartial(req);

        userProfileRepository.save(userProfile);  // cascade 설정해둬서 userProfile 도 함께 저장

        return UserProfileResDTO.from(userProfile);

    }
}
