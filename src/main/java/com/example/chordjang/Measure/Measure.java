package com.example.chordjang.Measure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;


@Entity
@Getter
@Table(name= "measures")
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer measureNo;
    String lyric;
    String chords; // "C/maj,C/maj,G/min,D/sus4"
    TimeSignature timeSignature;
    String info;
    String stroke;
}
