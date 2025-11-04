package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public UserResponseDTO findUserBy(String userId, String email) {
        if(userId!=null)
            return userRepository.findByUserId(userId).map(UserResponseDTO::fromEntity).orElse(null);
        else if(email!=null)
            return userRepository.findByEmail(email).map(UserResponseDTO::fromEntity).orElse(null);
        else
            throw new IllegalArgumentException("Unknown search type: ");
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        UserResponseDTO res =  userRepository.findById(id).map(UserResponseDTO::fromEntity).orElse(null);
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
