package com.example.chordjang.user;

import com.example.chordjang.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자를 필요로 합니다. 무분별한 생성을 막기 위해 protected로 설정합니다.
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, unique = true)
    String userId;
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RoleEnum role;


    @Builder
    public User(String userId, String password, String email, RoleEnum role){
        // id 는 .save() 호출 시점에 자동 저장됨. build 시점까지는 null
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
