package com.example.chordjang.user;

import com.example.chordjang.userProfile.UserProfile;
import com.example.chordjang.util.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 는 기본 생성자를 필요로 합니다. 무분별한 생성을 막기 위해 protected로 설정합니다.
public class User extends BaseEntity implements UserDetails {
    // Security 에서 UserDetails Interface 객체를 사용. 필수메서드 구현 필요.


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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id") // FK를 이 테이블에 둔다
    UserProfile userProfile;

    @Builder
    public User(String userId, String password, String email, RoleEnum role, UserProfile userProfile){
        // id 는 .save() 호출 시점에 자동 저장됨. build 시점까지는 null
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userProfile = userProfile;
    }

    public void setUserProfile(UserProfile userProfile){
        this.userProfile = userProfile;
        userProfile.setUser(this);
    }

    public void updateUser(String newEmail){
        if(newEmail != null)
            this.email =  newEmail;
    }

    // UserDetails 를 상속받기 때문에 Override 필요
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role.getKey()));
    }

    @Override
    public String getUsername() {
        return this.userId; // Spring Security에서는 username을 userId로 사용
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부 (true: 만료되지 않음)
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠금 여부 (true: 잠기지 않음)
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 여부 (true: 만료되지 않음)
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부 (true: 활성화됨)
    }
}
