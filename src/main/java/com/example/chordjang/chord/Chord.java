package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.UpdateChordReqDTO;
import com.example.chordjang.util.ChordValidator;
import com.example.chordjang.util.InstrumentType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "chords")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    InstrumentType type;
    String frets;
    // order G C E A?

    @Builder
    public Chord(String name, InstrumentType type, String frets){
        this.name = name;
        this.type = type;
        this.frets = frets;
    }

    public void updateChord(UpdateChordReqDTO req){
        if(req.getName() != null) this.name = req.getName();
        if(req.getType() != null) this.type = ChordValidator.convertType(req.getType());
        if(req.getFrets() != null && ChordValidator.isValidFrets(this.getType(), req.getFrets()))
            this.frets = req.getFrets();
    }
}
