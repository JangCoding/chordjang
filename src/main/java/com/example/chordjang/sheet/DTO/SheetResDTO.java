package com.example.chordjang.sheet.DTO;

import com.example.chordjang.bar.DTO.BarResDTO;
import com.example.chordjang.bar.Bar;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.sheet.Sheet;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
public class SheetResDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote songKey;
    Integer capo;
    String section; // Enum
    List<BarResDTO> barResList; // 마디

    public static SheetResDTO from(Sheet sheet){

        List<Bar> bars = sheet.getBars(); // 순서가 보장된 리스트

        // IntStream 을 사용한 루프
        List<BarResDTO> measureResList = IntStream.range(0,  bars.size())
                .mapToObj(i -> { // i는 0, 1, 2, ...
                    Bar barObj = bars.get(i); // 'i' 번째 칸에 있는 Measure 객체 (번호 정보 없음)
                    int measureNumber = i + 1;               // 'i' 번째 칸이므로, 마디 번호는 i + 1
                    return BarResDTO.from(barObj, measureNumber);
                })
                .collect(Collectors.toList());

        List<BarResDTO> barResDTOS = new ArrayList<>();

        return SheetResDTO.builder()
                .id(sheet.getId())
                .title(sheet.getTitle())
                .singer(sheet.getSinger())
                .writer(sheet.getWriter())
                .bpm(sheet.getBpm())
                .songKey(sheet.getSongKey())
                .capo(sheet.getCapo())
                .barResList(measureResList)
                .build();
    }
}
