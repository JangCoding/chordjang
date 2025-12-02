package com.example.chordjang.user;

import com.example.chordjang.exception.EmailAlreadyExistException;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.UserIdAlreadyExistException;
import com.example.chordjang.exception.UserNotFoundException;
import com.example.chordjang.user.DTO.CreateUserReqDTO;
import com.example.chordjang.user.DTO.UpdateUserReqDTO;
import com.example.chordjang.user.DTO.UserResDTO;
import com.example.chordjang.userProfile.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = false)
    @Override
    public UserResDTO createUser(CreateUserReqDTO req) {

        if (userRepository.findByUserId(req.getUserId()).isPresent())
            throw new UserIdAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_USERID);

        if (userRepository.findByEmail(req.getEmail()).isPresent())
            throw new EmailAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_EMAIL);

        User user = User.builder()
                .userId(req.getUserId())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword())) // 암호화
                .role(RoleEnum.USER)
                .build();

        UserProfile profile = UserProfile.builder().build(); // 초기값 세팅

        user.setUserProfile(profile);

        return UserResDTO.fromEntity(userRepository.save(user));
    }

    @Override
    public UserResDTO findUserBy(String userId, String email) {
        if (userId != null)
            return userRepository.findByUserId(userId)
                    .map(UserResDTO::fromEntity)
                    .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.USER_NOT_FOUND, "UserId", userId));
        if (email != null)
            return userRepository.findByEmail(email)
                    .map(UserResDTO::fromEntity)
                    .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.USER_NOT_FOUND, "Email", email));

        throw new IllegalArgumentException("입력 조건을 정확히 입력해주세요. ( UserId 와 Email 이 입력되지 않음. ) ");
    }

    @Override
    public UserResDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResDTO::fromEntity)
                .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.USER_NOT_FOUND, "Id", id));
    }

    @Override
    @Transactional
    public UserResDTO updateUser(String userId, UpdateUserReqDTO req) {

        //TODO 로그인 검증 과정 생략

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.USER_NOT_FOUND, "UserId", userId));

        user.updateUser(req.getEmail());

        return UserResDTO.fromEntity(user);   // @Transactional 변경 감지(Dirty Checking)되어 자동으로 DB에 반영.
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ErrorCodeEnum.USER_NOT_FOUND, "Id", id));
        userRepository.delete(user);
    }
}
