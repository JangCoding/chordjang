package com.example.chordjang.SheetPost.DTO;

import com.example.chordjang.SheetPost.SheetPost;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import lombok.Builder;

@Builder
public class SheetPostResDTO {
    Long postId;
    String title;
    String description;
    Long userId;
    String nickName;
    MusicSheetResDTO musicSheet;

    public static SheetPostResDTO from(SheetPost sheetPost) {
        return SheetPostResDTO.builder()
                .postId(sheetPost.getId())
                .title(sheetPost.getTitle())
                .description(sheetPost.getDescription())
                .userId(sheetPost.getUser().getId())
                .nickName(sheetPost.getUser().getNickName())
                .musicSheet(MusicSheetResDTO.from(sheetPost.getMusicSheet()))
                .build();
    }
}
