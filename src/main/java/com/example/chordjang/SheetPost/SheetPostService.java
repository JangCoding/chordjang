package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
import com.example.chordjang.reply.ReplyResDTO;

public interface SheetPostService {
    // SheetPost
    public SheetPostResDTO createSheetPost(String loginId, SheetPostReqDTO req);
    public SheetPostResDTO getSheetPost(Long id);
    public SheetPostResDTO updateSheetPost(SheetPostReqDTO req);
    public void deleteSheetPost(Long id);
    // Reply
    public ReplyResDTO createReply(String loginId, Long sheetPostId, String comment);
    public ReplyResDTO updateReply(Long sheetPostId, Long replyId, String comment);
    public ReplyResDTO getReply(Long sheetPostId,Long replyId);
    public ReplyResDTO deleteReply(Long sheetPostId, Long replyId);
}
