package com.example.chordjang.post.DTO;

import com.example.chordjang.sheet.DTO.SheetReqDTO;
import lombok.Getter;

@Getter
public class PostReqDTO {
    Long id;
    String title;
    String description;
    SheetReqDTO sheetReq;
}
