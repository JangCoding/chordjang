package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO req) {
        User user = User.builder()
                .userId(req.getUserId())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();

        return UserResponseDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public UserResponseDTO findUserByUserId(String userId) {
        return null;
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        return null;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(UpdateUserRequestDTO req) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
