package com.example.chordjang.musicSheet;

import com.example.chordjang.musicSheet.DTO.MusicSheetResDTO;
import com.example.chordjang.musicSheet.DTO.SearchMusicSheetReqDTO;
import com.example.chordjang.musicSheet.DTO.MusicSheetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/music-sheet")
@RequiredArgsConstructor
public class MusicSheetController {
    private final MusicSheetService musicSheetService;

    @PostMapping
    public ResponseEntity<MusicSheetResDTO> createMusicSheet(@RequestBody MusicSheetReqDTO req) {
        MusicSheetResDTO res = musicSheetService.createMusicSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicSheetResDTO> getMusicSheet(@PathVariable Long id) {
        MusicSheetResDTO res = musicSheetService.getMusicSheet(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<MusicSheetResDTO>> searchMusicSheet(@RequestBody SearchMusicSheetReqDTO req) {
        List<MusicSheetResDTO> res = musicSheetService.searchMusicSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MusicSheetResDTO> updateMusicSheet(@PathVariable Long id, @RequestBody MusicSheetReqDTO req) {
        MusicSheetResDTO res = musicSheetService.updateMusicSheet(id, req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusicSheet(@PathVariable Long id) {
        musicSheetService.deleteMusicSheet(id);
        return ResponseEntity.noContent().build();
    }
}
