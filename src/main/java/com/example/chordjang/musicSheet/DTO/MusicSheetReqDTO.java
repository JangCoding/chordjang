package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.Measure.DTO.MeasureReqDTO;
import com.example.chordjang.chord.RootNote;
import lombok.Getter;

import java.util.List;

@Getter
public class MusicSheetReqDTO {
    Long id;    // sheetPost 에서 update 시 사용.
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    List<MeasureReqDTO> measureReqDTOList; // 마디
}
