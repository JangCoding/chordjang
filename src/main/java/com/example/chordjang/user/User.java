package com.example.chordjang.user;

import com.example.chordjang.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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

}
