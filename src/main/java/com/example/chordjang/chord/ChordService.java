package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.ChordResDTO;
import com.example.chordjang.chord.DTO.CreateChordReqDTO;
import com.example.chordjang.chord.DTO.UpdateChordReqDTO;

import java.util.List;

public interface ChordService {
    ChordResDTO createChord(CreateChordReqDTO req);
    ChordResDTO getChord(Long id);
    List<ChordResDTO> getChordList(Long id, String name, String Type);
    ChordResDTO updateChord(UpdateChordReqDTO req);
    void deleteChord(Long id);
}
