package com.example.chordjang.musicSheet;

import com.example.chordjang.musicSheet.DTO.CreateMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.UpdateMusicSheetReqDTO;

import java.util.List;

public class MusicSheetServiceImpl implements MusicSheetService {
    @Override
    public MusicSheetResDTO createMusicSheet(CreateMusicSheetReqDTO req) {
        return null;
    }

    @Override
    public MusicSheetResDTO getMusicSheet(Long id) {
        return null;
    }

    @Override
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req) {
        return List.of();
    }

    @Override
    public MusicSheetResDTO updateMusicSheet(UpdateMusicSheetReqDTO req) {
        return null;
    }

    @Override
    public void deleteMusicSheet(Long id) {

    }
}
