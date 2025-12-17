package com.example.chordjang.sheet;

import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.sheet.DTO.SheetResDTO;
import com.example.chordjang.sheet.DTO.SearchSheetReqDTO;
import com.example.chordjang.sheet.DTO.SheetReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SheetServiceImpl implements SheetService {
    private final SheetRepository sheetRepository;


    @Override
    @Transactional
    public SheetResDTO createSheet(SheetReqDTO req) {
        return SheetResDTO.from(createEntity(req));
    }

    public Sheet createEntity(SheetReqDTO req) {
        Sheet newSheet = Sheet.from(req);
        return sheetRepository.save(newSheet);
    }

    @Override
    @Transactional
    public SheetResDTO getSheet(Long id) {
        return SheetResDTO.from(sheetRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Sheet", "Id", id)));
    }

    @Override
    @Transactional
    public List<SheetResDTO> searchSheet(SearchSheetReqDTO req) {
        return List.of();
    }

    @Override
    @Transactional
    public SheetResDTO updateSheet(SheetReqDTO req) {

        Sheet sheet = sheetRepository.findById(req.getId())
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Sheet", "Id", req.getId()));

        sheet.update(req);

        return SheetResDTO.from(sheetRepository.save(sheet));
    }

    @Override
    @Transactional
    public void deleteSheet(Long id) {

    }
}
