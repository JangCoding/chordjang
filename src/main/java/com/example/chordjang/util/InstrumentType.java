package com.example.chordjang.util;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InstrumentType {
    UKULELE("Ukulele"),
    GUITAR("Guitar");

    private final String type;
}