package com.example.chordjang.chord;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ChordRepository extends JpaRepository<Chord, Long> {
    List<Chord> getChordsByRootNoteAndQualityAndFrets(RootNote rootNote, Quality quality, String Fret);

    Optional<Chord> findById(Long id);

    List<Chord> findAll(Specification<Chord> spec);
}