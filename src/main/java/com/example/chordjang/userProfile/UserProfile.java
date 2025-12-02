package com.example.chordjang.userProfile;

import com.example.chordjang.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "userProfiles")
public class UserProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int score;
    private int point;
    private int level;
    private int exp;

    @Builder
    public UserProfile(){
        this.score = 0;
        this.point = 0;
        this.level = 0;
        this.exp = 0;
    }

    public void updateUserProfile(int score, int point, int level, int exp){
        this.score = score;
        this.point = point;
        this.level = level;
        this.exp = exp;
    }

}
