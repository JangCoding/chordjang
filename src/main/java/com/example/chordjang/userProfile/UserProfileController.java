package com.example.chordjang.userProfile;

import com.example.chordjang.userProfile.DTO.UpdateUserProfileReqDTO;
import com.example.chordjang.userProfile.DTO.UserProfileResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userProfile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping
    public ResponseEntity<UserProfileResDTO> getUserProfile(
            @AuthenticationPrincipal(expression = "username") String userId) {
        return ResponseEntity.ok().body(userProfileService.getUserProfile(userId));
    }

    @PutMapping
    public ResponseEntity<UserProfileResDTO> updateUserProfile(
            @AuthenticationPrincipal(expression = "username") String userId,
            @RequestBody UpdateUserProfileReqDTO req) {
        return ResponseEntity.ok().body(userProfileService.updateUserProfile(userId, req));
    }
}
