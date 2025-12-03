package com.example.chordjang.util;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.InvalidParameterException;

public class ChordValidator {
    private static final int UKULELE_SIZE = 4;
    private static final int GUITAR_SIZE = 6;

    public static InstrumentType convertType(String type){

        if (type == null || type.trim().isEmpty()) {
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "악기 타입은 필수입니다.", "InstrumentType", type);
        }

        try {
            return InstrumentType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD,
                    "지원하지 않는 악기 타입입니다.", "InstrumentType", type );
        }
    }

    public static boolean isValidFrets(InstrumentType type, String frets) {

        int fretSize = frets.split(",").length;

        if (type==InstrumentType.UKULELE && fretSize != UKULELE_SIZE)
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "운지법", "Frets", frets);

        if (type==InstrumentType.GUITAR && fretSize != GUITAR_SIZE)
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "운지법", "Frets", frets);

        return true;
    }
}
