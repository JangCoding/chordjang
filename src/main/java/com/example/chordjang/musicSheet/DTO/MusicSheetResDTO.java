package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.Measure.DTO.MeasureResDTO;
import com.example.chordjang.Measure.Measure;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.musicSheet.MusicSheet;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Builder
public class MusicSheetResDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    String section; // Enum
    List<MeasureResDTO> measure; // 마디

    public static MusicSheetResDTO fromEntity(MusicSheet musicSheet){

        List<Measure> measures = musicSheet.getMeasures(); // 순서가 보장된 리스트

        // IntStream 을 사용한 루프
        List<MeasureResDTO> measureResList = IntStream.range(0,  measures.size())
                .mapToObj(i -> { // i는 0, 1, 2, ...
                    Measure measureObject = measures.get(i); // 'i' 번째 칸에 있는 Measure 객체 (번호 정보 없음)
                    int measureNumber = i + 1;               // 'i' 번째 칸이므로, 마디 번호는 i + 1
                    return MeasureResDTO.fromEntity(measureObject, measureNumber);
                })
                .collect(Collectors.toList());

        List<MeasureResDTO> measureResDTOS = new ArrayList<>();

        return MusicSheetResDTO.builder()
                .id(musicSheet.getId())
                .title(musicSheet.getTitle())
                .singer(musicSheet.getSinger())
                .writer(musicSheet.getWriter())
                .bpm(musicSheet.getBpm())
                .songKey(musicSheet.getSongKey())
                .capo(musicSheet.getCapo())
                .measure(measureResList)
                .build();
    }
}
