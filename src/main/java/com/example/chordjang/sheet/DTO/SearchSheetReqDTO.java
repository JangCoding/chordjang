package com.example.chordjang.sheet.DTO;

import com.example.chordjang.chord.RootNote;

public class SearchSheetReqDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
}
