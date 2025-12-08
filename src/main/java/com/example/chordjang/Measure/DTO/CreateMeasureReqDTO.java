package com.example.chordjang.Measure.DTO;

import lombok.Getter;

@Getter
public class CreateMeasureReqDTO {
    // measureNo; 는 자동 넘버링
    String lyric;
    String chords;
    String timeSignature;
    String info;
    String stroke;
}
