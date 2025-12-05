package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.UpdateChordReqDTO;
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
    InstrumentType type;
    RootNote rootNote;
    Quality quality;
    String frets;
    // order G C E A?

    @Builder
    public Chord(RootNote rootNote, Quality quality, InstrumentType type, String frets){
        this.rootNote = rootNote;
        this.quality = quality;
        this.type = type;
        this.frets = frets;
    }

    public void updateChord(UpdateChordReqDTO req){
        if(req.getRootNote() != null) this.rootNote = req.getRootNote();
        if(req.getQuality() != null) this.quality = req.getQuality();
        if(req.getType() != null) this.type = ChordHepler.convertType(req.getType());
        if(req.getFrets() != null && ChordHepler.isValidFrets(this.getType(), req.getFrets()))
            this.frets = req.getFrets();
    }
}
