package com.example.chordjang.musicSheet;

import com.example.chordjang.Measure.Measure;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "music_sheets")
@NoArgsConstructor
public class MusicSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    @OneToMany(mappedBy = "musicSheet", cascade = CascadeType.ALL, orphanRemoval = true) // Cascade : 하위 항목들도 함께 저장
    @OrderColumn(name = "measure_no") // @OneToMany 관계에서 리스트의 순서를 데이터베이스에 저장하고 관리하기 위한 기능
    List<Measure> measures = new ArrayList<>(); // 마디

    @Builder
    public MusicSheet(String title, String singer, String writer, Integer bpm, RootNote songKey, Integer capo, List<Measure> measureList) {
        this.title = title;
        this.singer = singer;
        this.writer = writer;
        this.bpm = bpm;
        this.songKey = songKey;
        this.capo = capo;
        this.measures = measureList;
    }

    public static MusicSheet from(MusicSheetReqDTO req) {
        MusicSheet musicSheet = new MusicSheet();
        musicSheet.update(req);
        return musicSheet;
    }

    public void update(MusicSheetReqDTO req) {
        if (req.getTitle() != null) this.title = req.getTitle();
        if (req.getSinger() != null) this.singer = req.getSinger();
        if (req.getWriter() != null) this.writer = req.getWriter();
        if (req.getBpm() != null) this.bpm = req.getBpm();
        if (req.getSongKey() != null) this.songKey = req.getSongKey();
        if (req.getCapo() != null) this.capo = req.getCapo();
    }

    public void addMeasure(Measure measure) {
        this.getMeasures().add(measure);
    }
}
