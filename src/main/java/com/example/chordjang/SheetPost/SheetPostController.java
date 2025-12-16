package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
import com.example.chordjang.reply.ReplyResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sheet-post")
@RequiredArgsConstructor
public class SheetPostController {
    private final SheetPostServiceImpl sheetPostService;

    @PostMapping
    public ResponseEntity<SheetPostResDTO> createSheetPost(@AuthenticationPrincipal(expression = "username") Long userId, @RequestBody SheetPostReqDTO req) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.createSheetPost(userId, req));
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

    // Reply ----------------------------------------------------------------------------
    @PostMapping("/{sheetPostId}/reply")
    public ResponseEntity<ReplyResDTO> createReply(
            @AuthenticationPrincipal(expression = "username") Long userId,
            @PathVariable Long sheetPostId,
            @RequestParam String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.createReply(userId, sheetPostId, comment));
    }

    @GetMapping("/{sheetPostId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> getReply(@PathVariable Long replyId) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.getReply(replyId));
    }

    @PutMapping("/{sheetPostId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> updateReply(
            @PathVariable Long replyId,
            @RequestParam String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.updateReply(replyId, comment));
    }


    @DeleteMapping("/{sheetPostId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> deleteReply(
            @PathVariable Long replyId) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.deleteReply(replyId));
    }


}
