package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Quality;
import com.example.chordjang.chord.RootNote;
import lombok.Getter;

@Getter
public class SearchChordReqDTO {
    Long id;
    String type;
    RootNote rootNote;
    Quality quality;
}
