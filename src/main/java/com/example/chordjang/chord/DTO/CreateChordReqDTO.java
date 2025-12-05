package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Chord;
import com.example.chordjang.chord.ChordHepler;
import com.example.chordjang.chord.InstrumentType;
import com.example.chordjang.chord.Quality;
import com.example.chordjang.chord.RootNote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CreateChordReqDTO {
    @NotBlank(message = "코드명은 필수입니다.")
    @NotBlank(message = "악기타입은 필수입니다.")
    String type;
    RootNote rootNote;
    Quality quality;
    @NotBlank(message = "프렛 정보는 필수입니다.")
    @Pattern(
            regexp = "^([xX]|[0-9]{1,2})(,([xX]|[0-9]{1,2}))*$",
            message = "운지법 정보는 'x' 또는 1~2자리 숫자로 이루어져야 하며, 콤마(,)로 구분되어야 합니다. 예: 9,13,10,3"
    )
    String frets;

    public Chord toEntity(){
        InstrumentType convertedType = ChordHepler.convertType(this.getType());

        return Chord.builder()
                .rootNote(this.getRootNote())
                .quality(this.getQuality())
                .type(convertedType)
                .frets(this.getFrets())
                .build();
    }
}
