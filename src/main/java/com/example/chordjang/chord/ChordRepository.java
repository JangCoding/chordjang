package com.example.chordjang.chord;

import com.example.chordjang.util.InstrumentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChordRepository extends JpaRepository<Chord, Long> {
    List<Chord> getChordsByNameAndType(String name, InstrumentType type);
}
