package com.example.chordjang.Measure;

import com.example.chordjang.Measure.DTO.MeasureReqDTO;
import com.example.chordjang.musicSheet.MusicSheet;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name= "measures")
@NoArgsConstructor
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String lyric;
    String chords; // "C/maj,C/maj,G/min,D/sus4"
    TimeSignature timeSignature;
    String info;    // Repeat Start, end, D.S, Coda ..
    String stroke;  // Enum?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_sheet_id") // 테이블에 MusicSheet Id를 외래키(FK) 로 생성할 것. db의 필드 이름은 music_sheet_id
    MusicSheet musicSheet;               // 외래키를 갖고 있는 '주인'( JPA적 관점 )


    @Builder
    public Measure(Integer measureNo, String lyric, String chords, String timeSignature, String info, String stroke) {
        this.lyric = lyric;
        this.chords = chords;
        this.timeSignature = TimeSignature.valueOf(timeSignature);
        this.info = info;
        this.stroke = stroke;
    }

    public static Measure from(MeasureReqDTO req) {
        Measure measure = new Measure();
        measure.update(req);
        return measure;
    }

    public void update(MeasureReqDTO req){
        if(req.getLyric() != null)  this.lyric = req.getLyric();
        if(req.getChords() != null)  this.chords = req.getChords();
        if(req.getTimeSignature() != null)  this.timeSignature = TimeSignature.valueOf(req.getTimeSignature());
        if(req.getInfo() != null)  this.info = req.getInfo();
        if(req.getStroke() != null) this.stroke = req.getStroke();
    }

    public void setMusicSheet(MusicSheet musicSheet) {
        if (musicSheet != null)
            this.musicSheet = musicSheet;
    }
}
