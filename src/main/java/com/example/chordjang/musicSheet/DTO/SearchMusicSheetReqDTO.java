package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.chord.RootNote;
import jakarta.validation.constraints.NotNull;

public class SearchMusicSheetReqDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote key;
}
