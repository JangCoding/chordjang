package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserReqDTO;
import com.example.chordjang.user.DTO.UpdateUserReqDTO;
import com.example.chordjang.user.DTO.UserResDTO;


public interface UserService {

    UserResDTO createUser(CreateUserReqDTO req);

    UserResDTO findUserBy(String userId, String email);
    UserResDTO getUserById(Long id);

    UserResDTO updateUser(String userId, UpdateUserReqDTO req);

    void deleteUser(Long id);

}
