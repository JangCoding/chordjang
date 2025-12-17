package com.example.chordjang.post;

import com.example.chordjang.post.DTO.PostReqDTO;
import com.example.chordjang.post.DTO.PostResDTO;
import com.example.chordjang.exception.ErrorCodeEnum;
import com.example.chordjang.exception.TargetNotFoundException;
import com.example.chordjang.sheet.Sheet;
import com.example.chordjang.sheet.SheetService;
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
public class PostServiceImpl implements PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final SheetService sheetService;
    private final ReplyRepository replyRepository;

    @Override
    @Transactional
    public PostResDTO createPost(String loginId, PostReqDTO req) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "유저", "loginId", loginId));

        Post post = Post.from(req);

        Sheet sheet = sheetService.createEntity(req.getSheetReq());
        sheet.setPost(post);

        post.setUser(user);
        post.setSheet(sheet);

        return PostResDTO.from(postRepository.save(post));
    }

    @Override
    @Transactional
    public PostResDTO getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Post", "Id", id));

        return PostResDTO.from(post);
    }

    @Override
    @Transactional
    public PostResDTO updatePost(PostReqDTO req) {
        Post post = postRepository.findById(req.getId())
                .orElseThrow(() -> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "Post", "Id", req.getId()));

        post.update(req);

        if (req.getSheetReq() != null) {
            if (post.getSheet() != null)
                post.getSheet().update(req.getSheetReq());
            else {
                Sheet newSheet = Sheet.from(req.getSheetReq());
                newSheet.setPost(post);
                post.setSheet(newSheet);
            }
        }
        return PostResDTO.from(postRepository.save(post));
    }

    @Override
    @Transactional
    public void deletePost(Long id) {

    }

    // Reply ----------------------------------------------------------------------------
    @Override
    @Transactional
    public ReplyResDTO createReply(String loginId, Long postId, String comment){
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(()-> new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "유저", "loginId", loginId));

        Post post = postRepository.findById(postId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "게시글", "PostId", postId));

        Reply reply = new Reply();
        reply.setComment(comment);

        reply.setUser(user);
        reply.setPost(post);

        return ReplyResDTO.from(replyRepository.save(reply));
    }

    @Override
    @Transactional
    public ReplyResDTO updateReply(Long postId, Long replyId, String comment) {
        Reply reply = replyRepository.findByPost_IdAndId(postId, replyId)
                .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "댓글", "ReplyId", replyId));

        reply.update(comment);

        return ReplyResDTO.from(replyRepository.save(reply));
    }

    @Override
    @Transactional
    public ReplyResDTO getReply(Long postId, Long replyId) {
        Reply reply = replyRepository.findByPost_IdAndId(postId, replyId)
            .orElseThrow(()->new TargetNotFoundException(ErrorCodeEnum.TARGET_NOT_FOUND, "댓글", "ReplyId", replyId));

        return ReplyResDTO.from(reply);
    }

    @Override
    @Transactional
    public ReplyResDTO deleteReply(Long postId, Long replyId) {

        return null;
    }
}
