package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
import com.example.chordjang.reply.ReplyResDTO;

public interface SheetPostService {
    // SheetPost
    public SheetPostResDTO createSheetPost(Long userId, SheetPostReqDTO req);
    public SheetPostResDTO getSheetPost(Long id);
    public SheetPostResDTO updateSheetPost(Long id, SheetPostReqDTO req);
    public void deleteSheetPost(Long id);
    // Reply
    public ReplyResDTO createReply(Long userId, Long sheetPostId, String comment);
    public ReplyResDTO updateReply(Long replyId, String comment);
    public ReplyResDTO getReply(Long replyId);
    public ReplyResDTO deleteReply(Long replyId);
}
