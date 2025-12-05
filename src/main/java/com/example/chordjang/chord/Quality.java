package com.example.chordjang.chord;

import lombok.Getter;

@Getter
public enum Quality {
    // 3화음 (Triads)
    MAJ(""),        // C (메이저 코드는 일반적으로 기호를 생략합니다)
    MIN("m"),       // Cm
    DIM("dim"),     // Cdim
    AUG("aug"),     // Caug (+)

    // 7화음 (Sevenths)
    DOM7("7"),      // C7
    MAJ7("maj7"),   // Cmaj7 (CM7 또는 C∆7로 표기되기도 합니다)
    MIN7("m7"),     // Cm7
    MIN_MAJ7("m(maj7)"), // Cm(maj7)
    DIM7("dim7"),   // Cdim7 (C°7)
    HALF_DIM("m7(b5)"), // Cm7(b5) (Cø)
    AUG7("aug7"),   // Caug7 (C+7)

    // Suspendeds (3음 대체)
    SUS2("sus2"),   // Csus2
    SUS4("sus4"),   // Csus4 (C7sus4와 같이 7과 함께 쓰일 때도 Quality에 포함합니다)

    // 기타 (선택 사항)
    NINTH("9"),     // C9 (DOM7에 9th가 포함된 형태를 9th 코드로 따로 분류할 경우)
    MAJ9("maj9"),   // Cmaj9
    MIN9("m9"),     // Cm9
    ELEVENTH("11"), // C11
    THIRTEENTH("13");// C13

    private final String simbol;

    Quality(String simbol){
        this.simbol = simbol;
    }
}
