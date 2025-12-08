package com.example.chordjang.Measure.DTO;

import com.example.chordjang.Measure.Measure;
import com.example.chordjang.Measure.TimeSignature;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MeasureResDTO {
    Long id;
    Integer measureNo;
    String lyric;
    String chords;
    TimeSignature timeSignature;
    String info;
    String stroke;

    public static MeasureResDTO fromEntity(Measure measure){
        return MeasureResDTO.builder()
                .id(measure.getId())
                .measureNo(measure.getMeasureNo())
                .lyric(measure.getLyric())
                .chords(measure.getChords())
                .timeSignature(measure.getTimeSignature())
                .info(measure.getInfo())
                .stroke(measure.getStroke())
                .build();
    }
}
