package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO req){
        UserResponseDTO res = userService.createUser(req);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody CreateUserRequestDTO req){
        UserResponseDTO res = userService.createUser(req);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id){
      userService.deleteUser(id);
        return ResponseEntity.ok(res);
    }

}
