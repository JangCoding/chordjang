package com.example.chordjang.musicSheet;

import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;

import java.util.List;

public interface MusicSheetService {
    public MusicSheetResDTO createMusicSheet(MusicSheetReqDTO req);
    MusicSheet createEntity(MusicSheetReqDTO req);
    public MusicSheetResDTO getMusicSheet(Long id);
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req);
    public MusicSheetResDTO updateMusicSheet(MusicSheetReqDTO req);
    public void deleteMusicSheet(Long id);
}
