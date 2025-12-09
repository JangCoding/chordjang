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
        MusicSheet newMusicSheet = MusicSheet.from(req);

        for (int i=0; i < req.getMeasureReqDTOList().size();i++) {
            MeasureReqDTO reqDTO = req.getMeasureReqDTOList().get(i);
            Measure newMeasure = Measure.from(reqDTO);
            newMeasure.setMusicSheet(newMusicSheet);
            newMusicSheet.addMeasure(newMeasure);
        }

        musicSheetRepository.save(newMusicSheet);

        return MusicSheetResDTO.fromEntity(newMusicSheet);
    }

    @Override
    public MusicSheetResDTO getMusicSheet(Long id) {
        return MusicSheetResDTO.fromEntity(musicSheetRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.MUSIC_SHEET_NOT_FOUND, "MusicSheetId", id)));
    }

    @Override
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req) {
        return List.of();
    }

    @Override
    @Transactional
    public MusicSheetResDTO updateMusicSheet(Long id, MusicSheetReqDTO req) {

        MusicSheet musicSheet = musicSheetRepository.findById(id)
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.MUSIC_SHEET_NOT_FOUND, "MusicSheetId", id));

        Map<Long, Measure> measureMap = musicSheet.getMeasures().stream()
                .collect(Collectors.toMap(
                        Measure::getId,      // Key
                        Function.identity() // Value 그대로 사용
                ));

        for(int i=0;i<req.getMeasureReqDTOList().size();i++) {
            MeasureReqDTO measureReqDTO = req.getMeasureReqDTOList().get(i);
            Long measureId = measureReqDTO.getId();
            if(measureId != null) {  // 기존 마디 업데이트
                Measure measure = measureMap.get(measureId);
                if(measure != null) {
                    measure.update(measureReqDTO);
                    measureMap.remove(measureId);   // 처리 후 Map 에서 지움
                }
            } else {  // 새 마디 생성
                Measure measure = Measure.from(measureReqDTO);
                musicSheet.addMeasure(measure);
            }
        }
        musicSheet.getMeasures().removeAll(measureMap.values());
        return MusicSheetResDTO.fromEntity(musicSheetRepository.save(musicSheet));
    }

    @Override
    public void deleteMusicSheet(Long id) {

    }
}
