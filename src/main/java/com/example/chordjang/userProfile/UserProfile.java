package com.example.chordjang.userProfile;

import com.example.chordjang.user.User;
import com.example.chordjang.userProfile.DTO.UpdateUserProfileReqDTO;
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
@Table(name = "user_profiles")
public class UserProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private int point;
    private int level;
    private double exp;

    // 기본값 FetchType.LAZY 적용 중
    @OneToOne     // userProfile = User 엔티티 필드명
    @JoinColumn(name = "user_id") // FK를 이 테이블에 둔다
    private User user;

    @Builder
    public UserProfile(){
        this.score = 0;
        this.point = 0;
        this.level = 0;
        this.exp = 0;
    }

    public void updatePartial(UpdateUserProfileReqDTO req) {
        if (req.getScore() != null) this.score = req.getScore();
        if (req.getPoint() != null) this.point = req.getPoint();
        if (req.getLevel() != null) this.level = req.getLevel();
        if (req.getExp() != null) this.exp = req.getExp();
    }
}
