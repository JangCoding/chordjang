package com.example.chordjang.SheetPost.DTO;

import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import lombok.Getter;

@Getter
public class SheetPostReqDTO {
    Long id;
    String title;
    String description;
    MusicSheetReqDTO musicSheetReq;
}
