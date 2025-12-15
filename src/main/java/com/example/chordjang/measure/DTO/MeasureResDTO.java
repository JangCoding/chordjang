package com.example.chordjang.measure.DTO;

import com.example.chordjang.measure.Measure;
import com.example.chordjang.measure.TimeSignature;
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

    public static MeasureResDTO fromEntity(Measure measure, int measureNo){
        return MeasureResDTO.builder()
                .id(measure.getId())
                .measureNo(measureNo)
                .lyric(measure.getLyric())
                .chords(measure.getChords())
                .timeSignature(measure.getTimeSignature())
                .info(measure.getInfo())
                .stroke(measure.getStroke())
                .build();
    }
}
