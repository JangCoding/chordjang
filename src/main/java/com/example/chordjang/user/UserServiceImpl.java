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
    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public List<User> getUserList() {
        return List.of();
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO req) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(UpdateUserRequestDTO req) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {
    }
}
