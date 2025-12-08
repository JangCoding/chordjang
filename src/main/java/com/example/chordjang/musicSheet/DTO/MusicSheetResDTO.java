package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.Measure.Measure;
import com.example.chordjang.chord.RootNote;
import com.example.chordjang.musicSheet.MusicSheet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MusicSheetResDTO {
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote key;
    Integer capo;
    String section; // Enum
    @OneToMany(cascade = CascadeType.ALL)
    List<Measure> measure; // 마디

    public static MusicSheetResDTO fromEntity(MusicSheet musicSheet){
        return MusicSheetResDTO.builder()
                .id(musicSheet.getId())
                .title(musicSheet.getTitle())
                .singer(musicSheet.getSinger())
                .writer(musicSheet.getWriter())
                .bpm(musicSheet.getBpm())
                .key(musicSheet.getKey())
                .capo(musicSheet.getCapo())
                .build();
    }
}
