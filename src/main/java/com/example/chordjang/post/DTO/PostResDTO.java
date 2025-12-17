package com.example.chordjang.post.DTO;

import com.example.chordjang.post.Post;
import com.example.chordjang.sheet.DTO.SheetResDTO;
import lombok.Builder;

@Builder
public class PostResDTO {
    Long postId;
    String title;
    String description;
    Long userId;
    String nickName;
    SheetResDTO sheetReq;

    public static PostResDTO from(Post post) {
        return PostResDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .userId(post.getUser().getId())
                .nickName(post.getUser().getNickName())
                .sheetReq(SheetResDTO.from(post.getSheet()))
                .build();
    }
}
