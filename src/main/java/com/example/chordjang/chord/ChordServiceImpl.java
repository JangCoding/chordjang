package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.ChordResDTO;
import com.example.chordjang.chord.DTO.CreateChordReqDTO;
import com.example.chordjang.chord.DTO.SearchChordReqDTO;
import com.example.chordjang.chord.DTO.UpdateChordReqDTO;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.InvalidParameterException;
import com.example.chordjang.exception.TargetAlreadyExistException;
import com.example.chordjang.exception.TargetNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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
        InstrumentType type = ChordHepler.convertType(req.getType());
        String frets="";

        if(ChordHepler.isValidFrets(type, req.getFrets()))
            frets = req.getFrets();

        if(chordRepository.getChordsByRootNoteAndQualityAndFrets(req.getRootNote(), req.getQuality(), frets).isEmpty()) {
            return ChordResDTO.fromEntity( chordRepository.save(req.toEntity()) );
        }
        else {
            throw new TargetAlreadyExistException(ErrorCodeEnum.ALREADY_EXIST_CHORD,
                    "Chord", req.getType()+" "+req.getRootNote()+req.getType()+" "+req.getFrets());
        }
    }

    @Override
    public ChordResDTO getChord(Long id) {
        Chord chord = chordRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", id));
        return ChordResDTO.fromEntity(chord);
    }

    @Override
    public List<ChordResDTO> searchChord(SearchChordReqDTO req) {

        Specification<Chord> spec = Specification.unrestricted();

        if (req.getId() != null) {
            Chord chord = chordRepository.findById(req.getId())
                    .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", req.getId()));
            return List.of(ChordResDTO.fromEntity(chord));
        }

        if (req.getRootNote() != null)
            spec = spec.and(ChordSpecs.equalRootNote(req.getRootNote()));

        if (req.getQuality() != null)
            spec = spec.and(ChordSpecs.equalQuality(req.getQuality()));

        if(req.getType() != null)
            spec = spec.and(ChordSpecs.equalType(ChordHepler.convertType(req.getType())));

        return chordRepository.findAll(spec).stream().map(ChordResDTO::fromEntity).toList();
    }

    @Override
    @Transactional(readOnly = false)
    public ChordResDTO updateChord(UpdateChordReqDTO req) {
        Chord chord = chordRepository.findById(req.getId())
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", req.getId()));

        chord.updateChord(req);

        return ChordResDTO.fromEntity(chord);
    }

    @Override
    public void deleteChord(Long id) {
        Chord chord = chordRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.CHORD_NOT_FOUND, "Id", id));
        chordRepository.delete(chord);
    }
}
