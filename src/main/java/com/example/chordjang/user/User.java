package com.example.chordjang.user;

import com.example.chordjang.user.DTO.UpdateUserReqDTO;
import com.example.chordjang.userProfile.UserProfile;
import com.example.chordjang.util.BaseEntity;
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
    String loginId;         // userDetails.getUserName()
    @Column(nullable = false)
    String password;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false, unique = true)
    String nickName;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    RoleEnum role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    UserProfile userProfile;

    @Builder
    public User(String loginId, String password, String email, String nickName, RoleEnum role, UserProfile userProfile){
        // id 는 .save() 호출 시점에 자동 저장됨. build 시점까지는 null
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.role = role;
        this.userProfile = userProfile;
    }

    public void setUserProfile(UserProfile userProfile){
        this.userProfile = userProfile;
        userProfile.setUser(this);
    }

    public void update(UpdateUserReqDTO req){
        if(req.getEmail() != null)
            this.email =  req.getEmail();
        if(req.getNickName() != null)
            this.nickName = req.getNickName();
    }


    // UserDetails 를 상속받기 때문에 Override 필요 //
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role.getKey()));
    }

    @Override
    public String getUsername() {
        return this.loginId; // Spring Security에서는 username을 userId로 사용
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
