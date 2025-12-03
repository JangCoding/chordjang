package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.ChordResDTO;
import com.example.chordjang.chord.DTO.CreateChordReqDTO;
import com.example.chordjang.chord.DTO.UpdateChordReqDTO;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChordServiceImpl implements ChordService {

    private final ChordRepository chordRepository;


    @Override
    @Transactional(readOnly = false)
    public ChordResDTO createChord(CreateChordReqDTO req) {
        return ChordResDTO.fromEntity( chordRepository.save(req.toEntity()) );
    }

    @Override
    public ChordResDTO getChord(Long id) {
        return null;
    }

    @Override
    public List<ChordResDTO> getChordList(Long id, String name, String Type) {
        return List.of();
    }

    @Override
    @Transactional(readOnly = false)
    public ChordResDTO updateChord(UpdateChordReqDTO req) {
        Chord chord = chordRepository.findById(req.getId())
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", req.getId()));

        chord.updateChord(req);

        return ChordResDTO.builder().build();
    }

    @Override
    public void deleteChord(Long id) {
        Chord chord = chordRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", id));

        chordRepository.delete(chord);
    }
}
