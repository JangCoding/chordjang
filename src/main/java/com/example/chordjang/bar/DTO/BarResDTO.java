package com.example.chordjang.bar.DTO;

import com.example.chordjang.bar.Bar;
import com.example.chordjang.bar.TimeSignature;
import lombok.Builder;

@Builder
public class BarResDTO {
    Long id;
    Integer barNo;
    String lyric;
    String chords;
    TimeSignature timeSignature;
    String info;
    String stroke;

    public static BarResDTO from(Bar bar, int barNo){
        return BarResDTO.builder()
                .id(bar.getId())
                .barNo(barNo)
                .lyric(bar.getLyric())
                .chords(bar.getChords())
                .timeSignature(bar.getTimeSignature())
                .info(bar.getInfo())
                .stroke(bar.getStroke())
                .build();
    }
}
