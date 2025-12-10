package com.example.chordjang.Measure.DTO;

import lombok.Getter;

@Getter
public class MeasureReqDTO {
    Long id;
    String lyric;
    String chords;  // "C/maj,C/maj,G/min,D/sus4"
    String timeSignature;
    String info;
    String stroke;
}
