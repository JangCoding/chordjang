package com.example.chordjang.SheetPost;

import com.example.chordjang.SheetPost.DTO.SheetPostReqDTO;
import com.example.chordjang.SheetPost.DTO.SheetPostResDTO;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.musicSheet.MusicSheet;
import com.example.chordjang.musicSheet.MusicSheetService;
import com.example.chordjang.reply.Reply;
import com.example.chordjang.reply.ReplyRepository;
import com.example.chordjang.reply.ReplyResDTO;
import com.example.chordjang.user.User;
import com.example.chordjang.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SheetPostServiceImpl implements SheetPostService{
    private final UserRepository userRepository;
    private final SheetPostRepository sheetPostRepository;
    private final MusicSheetService musicSheetService;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
    public SheetPostResDTO createSheetPost(Long userId, SheetPostReqDTO req) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "유저", "UserId", userId));

        SheetPost sheetPost = SheetPost.from(req);

        MusicSheet musicSheet = musicSheetService.createEntity(req.getMusicSheetReq());
        musicSheet.setSheetPost(sheetPost);

        sheetPost.setUser(user);
        sheetPost.setMusicSheet(musicSheet);

        return SheetPostResDTO.from(sheetPostRepository.save(sheetPost));
    }

    @Override
    @Transactional
    public SheetPostResDTO getSheetPost(Long id) {
        SheetPost sheetPost = sheetPostRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "SheetPost", "Id", id));

        return SheetPostResDTO.from(sheetPost);
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
        return SheetPostResDTO.from(sheetPostRepository.save(sheetPost));
    }

    @Override
    @Transactional
    public void deleteSheetPost(Long id) {

    }

    // Reply ----------------------------------------------------------------------------
    @Override
    @Transactional
    public ReplyResDTO createReply(Long userId, Long sheetPostId, String comment){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "유저", "userId", userId));

        SheetPost sheetPost = sheetPostRepository.findById(sheetPostId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "게시글", "SheetPostId", userId));

        Reply reply = new Reply();
        reply.setComment(comment);

        reply.setUser(user);
        reply.setSheetPost(sheetPost);

        return ReplyResDTO.from(replyRepository.save(reply));
    }

    @Override
    @Transactional
    public ReplyResDTO updateReply(Long replyId, String comment) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "댓글", "ReplyId", replyId));

        reply.update(comment);

        return ReplyResDTO.from(replyRepository.save(reply));
    }

    @Override
    @Transactional
    public ReplyResDTO getReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "댓글", "ReplyId", replyId));

        return ReplyResDTO.from(reply);
    }

    @Override
    @Transactional
    public ReplyResDTO deleteReply(Long replyId) {
        return null;
    }
}
