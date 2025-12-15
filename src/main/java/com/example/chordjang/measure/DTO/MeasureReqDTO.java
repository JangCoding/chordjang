package com.example.chordjang.measure.DTO;

import lombok.Getter;

@Getter
public class MeasureReqDTO {
    Long id;
    String lyric;
    String chords;  // 사실상 List<Chord> "C/maj,C/maj,G/min,D/sus4"
    String timeSignature;
    String info;
    String stroke;
}
