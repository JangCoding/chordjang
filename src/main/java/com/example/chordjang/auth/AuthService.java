package com.example.chordjang.auth;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.exception.WrongPasswordException;
import com.example.chordjang.security.JwtTokenProvider;
import com.example.chordjang.user.User;
import com.example.chordjang.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenResponseDTO login(LoginRequestDTO req){
        User user = userRepository.findByLoginId(req.getUserId())
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "Id", req.getUserId() ));

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new WrongPasswordException(ErrorCodeEnum.INVALID_PASSWORD);

        String token = jwtTokenProvider.createToken(user.getLoginId(), user.getRole());
        return new TokenResponseDTO(token);
    }

}
