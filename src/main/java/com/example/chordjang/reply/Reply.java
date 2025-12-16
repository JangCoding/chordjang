package com.example.chordjang.reply;

import com.example.chordjang.SheetPost.SheetPost;
import com.example.chordjang.user.User;
import com.example.chordjang.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reply")
public class Reply extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sheet_post_id")
    SheetPost sheetPost;

    public void setUser(User user){
        if(user != null) this.user = user;
    }

    public void setSheetPost(SheetPost sheetPost){
        if(sheetPost != null) this.sheetPost = sheetPost;
    }

    public void update(String newComment) {
        if(newComment.isBlank()) this.comment = comment;
    }
}
