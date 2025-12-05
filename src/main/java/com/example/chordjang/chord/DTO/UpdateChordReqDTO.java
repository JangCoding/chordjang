package com.example.chordjang.chord.DTO;

import com.example.chordjang.chord.Quality;
import com.example.chordjang.chord.RootNote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateChordReqDTO {
    @NotBlank(message =  "ID 값은 필수입니다.")
    Long id;
    String type;
    RootNote rootNote;
    Quality quality;
    @Pattern(
            regexp = "^([xX]|[0-9]{1,2})(,([xX]|[0-9]{1,2}))*$",
            message = "운지법 정보는 'x' 또는 1~2자리 숫자로 이루어져야 하며, 콤마(,)로 구분되어야 합니다. 예: 9,13,10,3"
    )
    String frets;

}
