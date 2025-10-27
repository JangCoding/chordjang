package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(CreateUserRequestDTO req);

    UserResponseDTO findUserByUserId(String userId);
    UserResponseDTO findUserByEmail(String email);

    UserResponseDTO getUserById(Long id);
    UserResponseDTO updateUser(UpdateUserRequestDTO req);
    void deleteUser(Long id);

}
