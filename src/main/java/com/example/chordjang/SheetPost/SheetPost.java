package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.musicSheet.MusicSheet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    //Reply reply;

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
}
