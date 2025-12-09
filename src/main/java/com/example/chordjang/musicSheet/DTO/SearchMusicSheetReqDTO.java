package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.chord.RootNote;

public class SearchMusicSheetReqDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
}
