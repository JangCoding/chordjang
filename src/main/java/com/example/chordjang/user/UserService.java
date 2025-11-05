package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;


public interface UserService {

    UserResponseDTO createUser(CreateUserRequestDTO req);

    UserResponseDTO findUserBy(String userId, String email);
    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UpdateUserRequestDTO req);

    void deleteUser(Long id);

}
