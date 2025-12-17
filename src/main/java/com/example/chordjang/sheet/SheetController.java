package com.example.chordjang.sheet;

import com.example.chordjang.sheet.DTO.SheetResDTO;
import com.example.chordjang.sheet.DTO.SearchSheetReqDTO;
import com.example.chordjang.sheet.DTO.SheetReqDTO;
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
@RequestMapping("/sheet")
@RequiredArgsConstructor
public class SheetController {
    private final SheetService sheetService;

    @PostMapping
    public ResponseEntity<SheetResDTO> createSheet(@RequestBody SheetReqDTO req) {
        SheetResDTO res = sheetService.createSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetResDTO> getSheet(@PathVariable Long id) {
        SheetResDTO res = sheetService.getSheet(id);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/search")
    public ResponseEntity<List<SheetResDTO>> searchSheet(@RequestBody SearchSheetReqDTO req) {
        List<SheetResDTO> res = sheetService.searchSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PutMapping
    public ResponseEntity<SheetResDTO> updateSheet(@RequestBody SheetReqDTO req) {
        SheetResDTO res = sheetService.updateSheet(req);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheet(@PathVariable Long id) {
        sheetService.deleteSheet(id);
        return ResponseEntity.noContent().build();
    }
}
