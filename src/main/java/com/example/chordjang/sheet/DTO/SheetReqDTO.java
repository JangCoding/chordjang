package com.example.chordjang.sheet.DTO;

import com.example.chordjang.bar.DTO.BarReqDTO;
import com.example.chordjang.chord.RootNote;
import lombok.Getter;

import java.util.List;

@Getter
public class SheetReqDTO {
    Long id;    // sheetPost 에서 update 시 사용.
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    List<BarReqDTO> barReqDTOList; // 마디
}
