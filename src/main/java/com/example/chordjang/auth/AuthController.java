package com.example.chordjang.auth;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import com.example.chordjang.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@Valid @RequestBody CreateUserRequestDTO req){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(req));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO req){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(req));
    }
}
