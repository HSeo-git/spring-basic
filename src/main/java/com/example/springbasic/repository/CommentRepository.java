package com.example.springbasic.repository;

import com.example.springbasic.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //check all comments for a designated article
    @Query(value = "SELECT* FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    //check all comments written by a designated nickname
    List<Comment> findByNickname(String nickname);
}
