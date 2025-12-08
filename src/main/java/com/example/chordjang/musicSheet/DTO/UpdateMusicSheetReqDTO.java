package com.example.chordjang.musicSheet.DTO;

import com.example.chordjang.Measure.Measure;
import com.example.chordjang.chord.RootNote;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateMusicSheetReqDTO {
    @NotNull
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote key;
    Integer capo;
    @OneToMany(cascade = CascadeType.ALL)
    List<Measure> measure; // 마디
}
