package com.example.chordjang.musicSheet;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicSheetServiceImpl implements MusicSheetService {
    private final MusicSheetRepository musicSheetRepository;


    @Override
    @Transactional
    public MusicSheetResDTO createMusicSheet(MusicSheetReqDTO req) {
        return MusicSheetResDTO.from(createEntity(req));
    }

    public MusicSheet createEntity(MusicSheetReqDTO req) {
        MusicSheet newMusicSheet = MusicSheet.from(req);
        return musicSheetRepository.save(newMusicSheet);
    }

    @Override
    @Transactional
    public MusicSheetResDTO getMusicSheet(Long id) {
        return MusicSheetResDTO.from(musicSheetRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "MusicSheet", "Id", id)));
    }

    @Override
    @Transactional
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req) {
        return List.of();
    }

    @Override
    @Transactional
    public MusicSheetResDTO updateMusicSheet(MusicSheetReqDTO req) {

        MusicSheet musicSheet = musicSheetRepository.findById(req.getId())
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "MusicSheet", "Id", req.getId()));

        musicSheet.update(req);

        return MusicSheetResDTO.from(musicSheetRepository.save(musicSheet));
    }

    @Override
    @Transactional
    public void deleteMusicSheet(Long id) {

    }
}
