package com.example.chordjang.sheet;

import com.example.chordjang.sheet.DTO.SheetResDTO;
import com.example.chordjang.sheet.DTO.SearchSheetReqDTO;
import com.example.chordjang.sheet.DTO.SheetReqDTO;

import java.util.List;

public interface SheetService {
    public SheetResDTO createSheet(SheetReqDTO req);
    Sheet createEntity(SheetReqDTO req);
    public SheetResDTO getSheet(Long id);
    public List<SheetResDTO> searchSheet(SearchSheetReqDTO req);
    public SheetResDTO updateSheet(SheetReqDTO req);
    public void deleteSheet(Long id);
}
