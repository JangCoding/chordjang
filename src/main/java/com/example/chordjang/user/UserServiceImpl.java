package com.example.chordjang.user;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetAlreadyExistException;
import com.example.chordjang.exception.TargetNotFoundException;
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

        if (userRepository.findByLoginId(req.getLoginId()).isPresent())
            throw new TargetAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_TARGET, "유저", "UserID", req.getLoginId());

        if (userRepository.findByEmail(req.getEmail()).isPresent())
            throw new TargetAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_TARGET, "이메일", "Email", req.getEmail());

        if (userRepository.findByNickName(req.getNickName()).isPresent())
            throw new TargetAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_TARGET, "닉네임", "NickName", req.getNickName());

        User user = User.builder()
                .loginId(req.getLoginId())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword())) // 암호화
                .role(RoleEnum.USER)
                .build();

        UserProfile profile = UserProfile.builder().build(); // 초기값 세팅

        user.setUserProfile(profile);

        return UserResDTO.from(userRepository.save(user));
    }

    @Override
    public UserResDTO findUserBy(String loginId, String email) {
        if (loginId != null)
            return userRepository.findByLoginId(loginId)
                    .map(UserResDTO::from)
                    .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "Id", loginId));
        if (email != null)
            return userRepository.findByEmail(email)
                    .map(UserResDTO::from)
                    .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User",  "Email", email));

        throw new IllegalArgumentException("입력 조건을 정확히 입력해주세요. ( UserId 와 Email 이 입력되지 않음. ) ");
    }

    @Override
    public UserResDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserResDTO::from)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "Id", id));
    }

    @Override
    @Transactional
    public UserResDTO updateUser(String userId, UpdateUserReqDTO req) {

        //TODO 로그인 검증 과정 생략

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "UserId", userId));

        user.update(req);

        return UserResDTO.from(user);   // @Transactional 변경 감지(Dirty Checking)되어 자동으로 DB에 반영.
    }


    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "User", "Id", id));

        user.delete();
    }
}
