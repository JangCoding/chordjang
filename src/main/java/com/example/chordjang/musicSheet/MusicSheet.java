package com.example.chordjang.musicSheet;

import com.example.chordjang.Measure.Measure;
import com.example.chordjang.chord.RootNote;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
@Table(name = "music_sheets")
public class MusicSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String singer;
    String writer;
    Integer bpm; // Beats Per Minute
    RootNote key;
    Integer capo;
    @OneToMany(cascade = CascadeType.ALL)
    List<Measure> measure; // 마디
}
