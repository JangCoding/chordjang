package com.example.chordjang.post;

import com.example.chordjang.post.DTO.PostReqDTO;
import com.example.chordjang.post.DTO.PostResDTO;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostServiceImpl sheetPostService;

    @PostMapping
    public ResponseEntity<PostResDTO> createSheetPost(
            @AuthenticationPrincipal(expression = "username") String loginId, @RequestBody PostReqDTO req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sheetPostService.createPost(loginId, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResDTO> getSheetPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.getPost(id));
    }

    @PutMapping
    public ResponseEntity<PostResDTO> updateSheetPost(@RequestBody PostReqDTO req) {
        return ResponseEntity.status(HttpStatus.OK).body((sheetPostService.updatePost(req)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheetPost(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // Reply ----------------------------------------------------------------------------
    @PostMapping("/{postId}/reply")
    public ResponseEntity<ReplyResDTO> createReply(
            @AuthenticationPrincipal(expression = "username") String loginId,
            @PathVariable Long postId,
            @RequestParam String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.createReply(loginId, postId, comment));
    }

    @GetMapping("/{postId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> getReply(@PathVariable Long postId, @PathVariable Long replyId) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.getReply(postId, replyId));
    }

    @PutMapping("/{postId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> updateReply(
            @PathVariable Long postId,
            @PathVariable Long replyId,
            @RequestParam String comment) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.updateReply(postId, replyId, comment));
    }


    @DeleteMapping("/{postId}/reply/{replyId}")
    public ResponseEntity<ReplyResDTO> deleteReply(
            @PathVariable Long postId,
            @PathVariable Long replyId) {
        return ResponseEntity.status(HttpStatus.OK).body(sheetPostService.deleteReply(postId, replyId));
    }


}
