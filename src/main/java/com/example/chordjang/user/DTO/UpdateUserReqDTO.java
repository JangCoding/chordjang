package com.example.chordjang.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserReqDTO {
    Long id;
    @NotBlank(message = "이메일은 필수 입력 값입니다!")
    @Email(message = "이메일 형식이 올바르지 않습니다!")
    String email;
    @NotBlank(message = "이메일은 필수 입력 값입니다!")
    @Size(min=1, max=16, message = "닉네임은 1자 이상이어야 합니다!")
    String nickName;
}
