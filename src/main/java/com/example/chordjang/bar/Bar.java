package com.example.chordjang.bar;

import com.example.chordjang.bar.DTO.BarReqDTO;
import com.example.chordjang.sheet.Sheet;
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
@Table(name= "bars")
@NoArgsConstructor
public class Bar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String lyric;
    String chords; // "C/maj,C/maj,G/min,D/sus4"
    TimeSignature timeSignature;
    String info;    // Repeat Start, end, D.S, Coda ..
    String stroke;  // Enum?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sheet_id") // 테이블에 MusicSheet Id를 외래키(FK) 로 생성할 것. db의 필드 이름은 music_sheet_id
    Sheet sheet;               // 외래키를 갖고 있는 '주인'( JPA적 관점 )


    @Builder
    public Bar(Integer measureNo, String lyric, String chords, String timeSignature, String info, String stroke) {
        this.lyric = lyric;
        this.chords = chords;
        this.timeSignature = TimeSignature.valueOf(timeSignature);
        this.info = info;
        this.stroke = stroke;
    }

    public static Bar from(BarReqDTO req) {
        Bar bar = new Bar();
        bar.update(req);
        return bar;
    }

    public void update(BarReqDTO req){
        if(req.getLyric() != null)  this.lyric = req.getLyric();
        if(req.getChords() != null)  this.chords = req.getChords();
        if(req.getTimeSignature() != null)  this.timeSignature = TimeSignature.valueOf(req.getTimeSignature());
        if(req.getInfo() != null)  this.info = req.getInfo();
        if(req.getStroke() != null) this.stroke = req.getStroke();
    }

    public void setSheet(Sheet sheet) {
        if (sheet != null)
            this.sheet = sheet;
    }
}
