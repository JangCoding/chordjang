package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;

import java.util.List;

public interface UserService {

    User getUser(String userId);
    List<User> getUserList();

    UserResponseDTO createUser(CreateUserRequestDTO req);
    UserResponseDTO updateUser(UpdateUserRequestDTO req);
    void deleteUser(Long id);

}
