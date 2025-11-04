package com.example.chordjang.user;

import com.example.chordjang.user.DTO.CreateUserRequestDTO;
import com.example.chordjang.user.DTO.UpdateUserRequestDTO;
import com.example.chordjang.user.DTO.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO req) {

        if(userRepository.findByUserId(req.getUserId()).isPresent())
            throw new IllegalStateException("이미 사용중인 ID 입니다.");

        if(userRepository.findByEmail(req.getEmail()).isPresent())
            throw new IllegalStateException("이미 사용중인 Email 입니다.");

        User user = User.builder()
                .userId(req.getUserId())
                .email(req.getEmail())
                .password(req.getPassword())
                .role(RoleEnum.USER)
                .build();

        return UserResponseDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public UserResponseDTO findUserBy(String userId, String email) {
        if(userId!=null)
            return userRepository.findByUserId(userId)
                    .map(UserResponseDTO::fromEntity)
                    .orElseThrow(() -> new NoSuchElementException("UserId 에 해당하는 유저가 없습니다."));
        if(email!=null)
            return userRepository.findByEmail(email)
                    .map(UserResponseDTO::fromEntity)
                    .orElseThrow(() -> new NoSuchElementException("Email 에 해당하는 유저가 없습니다."));

        throw new IllegalArgumentException("입력 조건을 정확히 입력해주세요.");
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResponseDTO::fromEntity)
                .orElseThrow(() -> new NoSuchElementException("ID 값에 해당하는 유저가 없습니다."));
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UpdateUserRequestDTO req) {

        //TODO 로그인 검증 과정 생략

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not Found"));



        return null;
    }


    @Override
    public void deleteUser(Long id) {

    }
}
