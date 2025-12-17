package com.example.chordjang.post;

import com.example.chordjang.post.DTO.PostReqDTO;
import com.example.chordjang.post.DTO.PostResDTO;
import com.example.chordjang.reply.ReplyResDTO;

public interface PostService {
    // SheetPost
    public PostResDTO createPost(String loginId, PostReqDTO req);
    public PostResDTO getPost(Long id);
    public PostResDTO updatePost(PostReqDTO req);
    public void deletePost(Long id);
    // Reply
    public ReplyResDTO createReply(String loginId, Long postId, String comment);
    public ReplyResDTO updateReply(Long postId, Long replyId, String comment);
    public ReplyResDTO getReply(Long postId,Long replyId);
    public ReplyResDTO deleteReply(Long postId, Long replyId);
}
