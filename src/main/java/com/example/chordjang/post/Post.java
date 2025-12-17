package com.example.chordjang.post;

import com.example.chordjang.post.DTO.PostReqDTO;
import com.example.chordjang.sheet.Sheet;
import com.example.chordjang.reply.Reply;
import com.example.chordjang.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String title;
    String description;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    Sheet sheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    //Reply reply;
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Reply> replyList = new ArrayList<>();

    @Builder
    public Post(String title, String description, Sheet sheet) {
        this.title = title;
        this.description = description;
        this.sheet = sheet;
    }

    public static Post from(PostReqDTO req){
        Post post = new Post();
        post.update(req);
        return post;
    }

    public void update(PostReqDTO req){
        if(req.getTitle() != null) this.title = req.getTitle();
        if(req.getDescription() != null) this.description = req.getDescription();
        if(req.getSheetReq() != null) this.sheet = Sheet.from(req.getSheetReq());
    }

    public void setSheet(Sheet sheet) {
        if(sheet != null) this.sheet = sheet;
    }

    public void setUser(User user) {
        if( user != null ) this.user = user;
    }
}
