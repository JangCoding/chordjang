package com.example.chordjang.chord;

import com.example.chordjang.chord.DTO.ChordResDTO;
import com.example.chordjang.chord.DTO.CreateChordReqDTO;
import com.example.chordjang.chord.DTO.SearchChordReqDTO;
import com.example.chordjang.chord.DTO.UpdateChordReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chords")
public class ChordController {

    private final ChordService chordService;

    @PostMapping
    public ResponseEntity<ChordResDTO> createChord(@RequestBody CreateChordReqDTO req){
        ChordResDTO res = chordService.createChord(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping
    public ResponseEntity<ChordResDTO> getChord(@RequestParam Long id){
        ChordResDTO res = chordService.getChord(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<ChordResDTO>> searchChord(@RequestBody SearchChordReqDTO req){
        List<ChordResDTO> res = chordService.searchChord(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/update")
    public ResponseEntity<ChordResDTO> updateChord(@RequestBody UpdateChordReqDTO req){
        ChordResDTO res = chordService.updateChord(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PutMapping("/delete")
    public ResponseEntity<Void> deleteChord(@RequestParam Long id){
        chordService.deleteChord(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
