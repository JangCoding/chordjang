package com.example.chordjang.SheetPost.DTO;

import com.example.chordjang.SheetPost.SheetPost;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SheetPostResDTO {
    Long id;
    String title;
    String description;
    MusicSheetResDTO musicSheet;

    public static SheetPostResDTO fromEntity(SheetPost sheetPost) {
        return SheetPostResDTO.builder()
                .id(sheetPost.getId())
                .title(sheetPost.getTitle())
                .description(sheetPost.getDescription())
                .musicSheet(MusicSheetResDTO.fromEntity(sheetPost.getMusicSheet()))
                .build();
    }
}
