package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.musicSheet.MusicSheet;
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
import org.hibernate.annotations.Fetch;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sheet_posts")
public class SheetPost {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String title;
    String description;

    @OneToOne(mappedBy = "sheetPost", cascade = CascadeType.ALL)
    MusicSheet musicSheet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    //Reply reply;
    @OneToMany(mappedBy = "sheetPost", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Reply> replyList = new ArrayList<>();

    @Builder
    public SheetPost(String title, String description, MusicSheet musicSheet) {
        this.title = title;
        this.description = description;
        this.musicSheet = musicSheet;
    }

    public static SheetPost from(SheetPostReqDTO req){
        SheetPost sheetPost = new SheetPost();
        sheetPost.update(req);
        return sheetPost;
    }

    public void update(SheetPostReqDTO req){
        if(req.getTitle() != null) this.title = req.getTitle();
        if(req.getDescription() != null) this.description = req.getDescription();
        if(req.getMusicSheetReq() != null) this.musicSheet = MusicSheet.from(req.getMusicSheetReq());
    }

    public void setMusicSheet(MusicSheet musicSheet) {
        if(musicSheet != null) this.musicSheet = musicSheet;
    }

    public void setUser(User user) {
        if( user != null ) this.user = user;
    }
}
