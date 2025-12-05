package com.example.chordjang.chord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChordRepository extends JpaRepository<Chord, Long> {
    List<Chord> getChordsByRootNoteAndQualityAndFrets(RootNote rootNote, Quality quality, String Fret);
}