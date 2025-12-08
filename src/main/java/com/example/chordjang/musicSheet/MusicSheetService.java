package com.example.chordjang.musicSheet;

import com.example.chordjang.musicSheet.DTO.CreateMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.UpdateMusicSheetReqDTO;

import java.util.List;

public interface MusicSheetService {
    public MusicSheetResDTO createMusicSheet(CreateMusicSheetReqDTO req);
    public MusicSheetResDTO getMusicSheet(Long id);
    public List<MusicSheetResDTO> searchMusicSheet(SearchMusicSheetReqDTO req);
    public MusicSheetResDTO updateMusicSheet(UpdateMusicSheetReqDTO req);
    public void deleteMusicSheet(Long id);
}
