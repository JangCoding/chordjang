package com.example.chordjang.bar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TimeSignature {
    FOUR_FOUR(4, 4),      // 4/4
    THREE_FOUR(3, 4),     // 3/4
    TWO_FOUR(2, 4),       // 2/4
    SIX_EIGHT(6, 8),      // 6/8
    TWELVE_EIGHT(12, 8);  // 12/8

    private final int bpm; // BeatsPerMeasure;
    private final int bu; // BeatUnit;
}