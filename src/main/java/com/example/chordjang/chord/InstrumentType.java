package com.example.chordjang.chord;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstrumentType {
    UKULELE("Ukulele", 4),
    GUITAR("Guitar", 6);

    private final String type;
    private final int fretSize;
}
