package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
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

@RestController
@RequestMapping("/sheet-post")
@RequiredArgsConstructor
public class SheetPostController {
    private final SheetPostServiceImpl sheetPostService;

    @PostMapping
    public ResponseEntity<SheetPostResDTO> createSheetPost(@RequestBody SheetPostReqDTO req) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.createSheetPost(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SheetPostResDTO> getSheetPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.getSheetPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SheetPostResDTO> updateSheetPost(@PathVariable Long id, @RequestBody SheetPostReqDTO req) {
        return ResponseEntity.status(HttpStatus.OK).body((sheetPostService.updateSheetPost(id, req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheetPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
