package com.example.chordjang.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findBySheetPost_IdAndId(Long sheetPostId, Long id);
}
