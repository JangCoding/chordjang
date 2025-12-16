package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Chord;
import com.example.chordjang.chord.ChordHepler;
import com.example.chordjang.chord.InstrumentType;
import com.example.chordjang.chord.RootNote;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;

@Builder
public class ChordResDTO {
    Long id;
    InstrumentType type;
    RootNote rootNote;
    String quality;
    Map<String,String> fretMap;

    public static ChordResDTO from(Chord chord){
        return ChordResDTO.builder()
                .id(chord.getId())
                .type(chord.getType())
                .rootNote(chord.getRootNote())
                .quality(chord.getQuality().getSimbol())
                .fretMap(ChordHepler.setFretMap(chord.getType(), chord.getFrets()))
                .build();
    }
}

