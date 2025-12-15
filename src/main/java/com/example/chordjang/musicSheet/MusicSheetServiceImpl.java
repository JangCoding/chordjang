package com.example.chordjang.musicSheet;

import com.example.chordjang.Measure.DTO.MeasureReqDTO;
import com.example.chordjang.Measure.Measure;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class MusicSheetServiceImpl implements MusicSheetService {
    private final MusicSheetRepository musicSheetRepository;


    @Override
    @Transactional
    public MusicSheetResDTO createMusicSheet(MusicSheetReqDTO req) {
        return MusicSheetResDTO.fromEntity(createEntity(req));
    }

    public MusicSheet createEntity(MusicSheetReqDTO req) {
        MusicSheet newMusicSheet = MusicSheet.from(req);
        return musicSheetRepository.save(newMusicSheet);
    }

    @Override
    public MusicSheetResDTO getMusicSheet(Long id) {
        return MusicSheetResDTO.fromEntity(musicSheetRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "MusicSheet", "Id", id)));
    }

    @Override
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req) {
        return List.of();
    }

    @Override
    @Transactional
    public MusicSheetResDTO updateMusicSheet(Long id, MusicSheetReqDTO req) {

        MusicSheet musicSheet = musicSheetRepository.findById(id)
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "MusicSheet", "Id", id));

        musicSheet.update(req);

        return MusicSheetResDTO.fromEntity(musicSheetRepository.save(musicSheet));
    }

    @Override
    public void deleteMusicSheet(Long id) {

    }
}
