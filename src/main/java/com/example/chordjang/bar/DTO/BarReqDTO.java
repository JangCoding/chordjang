package com.example.chordjang.bar.DTO;

import lombok.Getter;

@Getter
public class BarReqDTO {
    Long id;
    String lyric;
    String chords;  // 사실상 List<Chord> "C/maj,C/maj,G/min,D/sus4"
    String timeSignature;
    String info;
    String stroke;
}
