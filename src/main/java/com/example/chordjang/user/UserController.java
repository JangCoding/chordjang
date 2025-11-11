package com.example.chordjang.user;

import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getMyInfo(@AuthenticationPrincipal(expression = "username") String UserId) {
        UserResponseDTO res = userService.findUserBy(UserId, null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserResponseDTO> updateUser(
            @AuthenticationPrincipal(expression = "username") String userId,
            @RequestBody UpdateUserRequestDTO req) {
        UserResponseDTO res = userService.updateUser(userId, req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> findUserBy(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String email){

            UserResponseDTO res = userService.findUserBy(userId, email);
            return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO res = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res); // 응답 본문에 데이터 담아 보낼 때
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
      userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 본문 없이 상태 코드와 헤더만 보낼 때
    }

}
