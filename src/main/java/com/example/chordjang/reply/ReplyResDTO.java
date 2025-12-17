package com.example.chordjang.reply;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ReplyResDTO {
    Long id;
    String comment;
    Long userId;
    String nickName;
    Long postId;
    LocalDateTime updateAt;

    public static ReplyResDTO from(Reply reply){
        return ReplyResDTO.builder()
            .id(reply.getId())
            .comment(reply.getComment())
            .userId(reply.getUser().getId())
            .nickName(reply.getUser().getNickName())
            .postId(reply.getPost().getId())
            .updateAt(reply.getUpdatedAt())
            .build();
    }
}
