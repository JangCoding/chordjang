package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;

public interface SheetPostService {
    public SheetPostResDTO createSheetPost(SheetPostReqDTO req);
    public SheetPostResDTO getSheetPost(Long id);
    public SheetPostResDTO updateSheetPost(Long id, SheetPostReqDTO req);
    public void deleteSheetPost(Long id);
}
