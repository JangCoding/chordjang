package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO req){
        UserResponseDTO res = userService.createUser(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(res); // 응답 본문에 데이터 담아 보낼 때
    }

    @GetMapping
    public ResponseEntity<UserResponseDTO> findUserBy(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String email){

            UserResponseDTO res = userService.findUserBy(userId, email);

            if(res!=null)
                return ResponseEntity.status(HttpStatus.OK).body(res); // 응답 본문에 데이터 담아 보낼 때

            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        UserResponseDTO res = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(res); // 응답 본문에 데이터 담아 보낼 때
    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO req){
        UserResponseDTO res = userService.updateUser(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
      userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 본문 없이 상태 코드와 헤더만 보낼 때
    }

}
