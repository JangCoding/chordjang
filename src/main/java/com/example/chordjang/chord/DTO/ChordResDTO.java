package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Chord;
import com.example.chordjang.chord.ChordHepler;
import com.example.chordjang.chord.InstrumentType;
import com.example.chordjang.chord.Quality;
import com.example.chordjang.chord.RootNote;
import lombok.Builder;
import lombok.Getter;
import java.util.Map;

@Getter
@Builder
public class ChordResDTO {
    Long id;
    InstrumentType type;
    RootNote rootNote;
    Quality quality;
    Map<String,String> fretMap;

    public static ChordResDTO fromEntity(Chord chord){

        return ChordResDTO.builder()
                .id(chord.getId())
                .rootNote(chord.getRootNote())
                .quality(chord.getQuality())
                .type(chord.getType())
                .fretMap(ChordHepler.setFretMap(chord.getType(), chord.getFrets()))
                .build();
    }
}

