package com.example.chordjang.musicSheet;

import com.example.chordjang.musicSheet.DTO.CreateMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.UpdateMusicSheetReqDTO;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/musicSheet")
@NoArgsConstructor
public class MusicSheetController {
    private MusicSheetService musicSheetService;

    @PostMapping
    public ResponseEntity<MusicSheetResDTO> createMusicSheet(@RequestBody CreateMusicSheetReqDTO req) {
        MusicSheetResDTO res = musicSheetService.createMusicSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<MusicSheetResDTO> getMusicSheet(@RequestParam Long id) {
        MusicSheetResDTO res = musicSheetService.getMusicSheet(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping
    public ResponseEntity<List<MusicSheetResDTO>> searchMusicSheet(@RequestBody SearchMusicSheetReqDTO req) {
        List<MusicSheetResDTO> res = musicSheetService.searchMusicSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PostMapping
    public ResponseEntity<MusicSheetResDTO> updateMusicSheet(@RequestBody UpdateMusicSheetReqDTO req) {
        MusicSheetResDTO res = musicSheetService.updateMusicSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMusicSheet(Long id) {
        musicSheetService.deleteMusicSheet(id);
        return ResponseEntity.noContent().build();
    }
}
