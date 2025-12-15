package com.example.chordjang.musicSheet;

import com.example.chordjang.measure.DTO.MeasureReqDTO;
import com.example.chordjang.measure.Measure;
import com.example.chordjang.SheetPost.SheetPost;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    @OneToOne
    @JoinColumn(name = "sheet_post_id")
    SheetPost sheetPost;

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

        Map<Long, Measure> measureMap = this.getMeasures().stream()
                .filter(measure -> measure.getId() != null)
                .collect(Collectors.toMap(
                        Measure::getId,      // Key
                        Function.identity() // Value 그대로 사용
                ));

        for(int i=0;i<req.getMeasureReqDTOList().size();i++) {
            MeasureReqDTO measureReqDTO = req.getMeasureReqDTOList().get(i);
            Long measureId = measureReqDTO.getId();
            if(measureId != null) {  // 기존 마디 업데이트
                Measure measure = measureMap.get(measureId);
                if(measure == null) {
                    throw new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Measure", "Id", measureId);
                }
                measure.update(measureReqDTO);
                measureMap.remove(measureId);   // 처리 후 Map 에서 지움
            } else {  // 새 마디 생성
                Measure measure = Measure.from(measureReqDTO);
                measure.setMusicSheet(this);
                this.addMeasure(measure);
            }
        }
        this.getMeasures().removeAll(measureMap.values());
    }

    public void addMeasure(Measure measure) {
        this.getMeasures().add(measure);
    }

    public void setSheetPost(SheetPost sheetPost) {
        if (sheetPost != null) this.sheetPost = sheetPost;
    }
}
