package com.example.chordjang.userProfile.DTO;

import lombok.Getter;

@Getter
public class UpdateUserProfileReqDTO {
    private Integer score;  // null 구분하고 싶으니 매퍼 클래스로 선언.
    private Integer point;
    private Integer level;
    private Double exp;
}
