package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Chord;
import com.example.chordjang.util.InstrumentType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChordResDTO {
    Long id;
    String name;
    InstrumentType type;
    String frets;

    public static ChordResDTO fromEntity(Chord chord){
        return ChordResDTO.builder()
                .id(chord.getId())
                .name(chord.getName())
                .type(chord.getType())
                .frets(chord.getFrets())
                .build();
    }

}
