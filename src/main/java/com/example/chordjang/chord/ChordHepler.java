package com.example.chordjang.chord;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.InvalidParameterException;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChordHepler {

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

    public static RootNote convertRootNote(String rootNote){
        if (rootNote == null || rootNote.isEmpty()) {
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "근음은 필수입니다.", "RootNote", rootNote);
        }

        try {
            return RootNote.valueOf(rootNote.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD,
                    "지원하지 않는 근음입니다. (C,D,E,F,G,A,B)", "RootNote", rootNote );
        }
    }

    public static Quality convertQualtiy(String quality){
        try {
            if ( quality.equals("MAJ"))
                return Quality.MAJ;
            else
                return Quality.valueOf(quality.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD,
                    "지원하지 않는 성질입니다.", "Quality", quality );
        }
    }

    public static boolean isValidFrets(InstrumentType type, String frets) {

        String[] parts = frets.split(",");

        if (Arrays.stream(parts).anyMatch(String::isBlank)) {   // 나눴을 때 하나라도 빈칸이 있다면
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "운지법", "Frets", frets);
        }

        int fretSize = parts.length;

        if (fretSize != type.getFretSize())
            throw new InvalidParameterException(ErrorCodeEnum.INVALID_DTO_FIELD, "운지법", "Frets", frets);

        return true;
    }

    private static final String[] UKULELE_ORDER = {"G4","C3","E2","A1"};
    private static final String[] GUITAR_ORDER = {"E6","A5","D4","G3","B2","E1"};

    public static Map<String, String> setFretMap(InstrumentType type, String fretString){

        Map<String, String> fretMap = new LinkedHashMap<>();
        String[] fretList = fretString.split(",");
        String[] order;

        switch(type){
            case UKULELE-> order = UKULELE_ORDER;
            case GUITAR-> order = GUITAR_ORDER;
            default -> order = null;
        }

        for(int i=0;i<order.length;i++){
            fretMap.put(order[i], fretList[i]);
        }

        return fretMap;
    }
}
