package com.example.springbasic.service;

import com.example.springbasic.dto.CommentDto;
import com.example.springbasic.entity.Article;
import com.example.springbasic.entity.Comment;
import com.example.springbasic.repository.ArticleRepository;
import com.example.springbasic.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional //it deals with db, so it needs roll-back when error happens.
    public CommentDto create(Long articleId, CommentDto dto) {


        //check the article and make exception
        //if error happens this phase, it doesn't go to the next steps(to do this use ElseThrow).
        Article article = articleRepository.findById(articleId).orElseThrow(()->new IllegalArgumentException("Fail to create! No article"));

        //create comment entity
        Comment comment = Comment.createComment(dto, article);

        //save the entity in DB
        Comment created = commentRepository.save(comment);

        //response with DTO
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //check comment and make exception
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Fail to update! no comment"));
        //update comment
        target.patch(dto);
        //resisted updated comment to DB
        Comment updated = commentRepository.save(target);
        //change the entity into dto and return
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //check comment and make exception
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fail to delete no comment"));
        //delete comment from db
        commentRepository.delete(target);
        //response
        return CommentDto.createCommentDto(target);
    }
}
