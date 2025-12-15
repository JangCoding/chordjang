package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.musicSheet.MusicSheet;
import com.example.chordjang.musicSheet.MusicSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class SheetPostServiceImpl implements SheetPostService{
    private final SheetPostRepository sheetPostRepository;
    private final MusicSheetService musicSheetService;

    @Override
    @Transactional
    public SheetPostResDTO createSheetPost(SheetPostReqDTO req) {
        SheetPost sheetPost = SheetPost.from(req);

        MusicSheet musicSheet = musicSheetService.createEntity(req.getMusicSheetReq());
        musicSheet.setSheetPost(sheetPost);
        sheetPost.setMusicSheet(musicSheet);

        return SheetPostResDTO.fromEntity(sheetPostRepository.save(sheetPost));
    }

    @Override
    public SheetPostResDTO getSheetPost(Long id) {
        SheetPost sheetPost = sheetPostRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "SheetPost", "Id", id));

        return SheetPostResDTO.fromEntity(sheetPost);
    }

    @Override
    @Transactional
    public SheetPostResDTO updateSheetPost(Long id, SheetPostReqDTO req) {
        SheetPost sheetPost = sheetPostRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "SheetPost", "Id", id));

        sheetPost.update(req);

        if (req.getMusicSheetReq() != null) {
            if (sheetPost.getMusicSheet() != null)
                sheetPost.getMusicSheet().update(req.getMusicSheetReq());
            else {
                MusicSheet newMusicSheet = MusicSheet.from(req.getMusicSheetReq());
                newMusicSheet.setSheetPost(sheetPost);
                sheetPost.setMusicSheet(newMusicSheet);
            }
        }
        return SheetPostResDTO.fromEntity(sheetPostRepository.save(sheetPost));
    }

    @Override
    @Transactional
    public void deleteSheetPost(Long id) {

    }
}
