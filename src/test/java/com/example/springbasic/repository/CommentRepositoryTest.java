package com.example.springbasic.repository;

import com.example.springbasic.entity.Article;
import com.example.springbasic.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //Test reflects JPA
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("all comments for a designated article")
    void findByArticleId() {
        /*Case 1: all comments for the 4th article*/
        {
            //prepare input
            Long articleId = 4L;
            //execution
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //expectation
            Article article = new Article(4L, "Soul food?", "go comment");
            Comment a = new Comment(1L, article, "Kim", "Chicken");
            Comment b = new Comment(2L, article, "Choi", "Curry");
            Comment c = new Comment(3L, article, "Lee", "Pasta");
            List<Comment> expected = Arrays.asList(a, b, c);

            //check
            assertEquals(expected.toString(), comments.toString(), "all comments for Q4");
        }
        /*Case 2: all comments for the first article*/
        {
            //prepare input
            Long articleId = 1L;
            //execution
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            //expectation
            Article article = new Article(1L, "abc", "def");
            List<Comment> expected = Arrays.asList();

            //check
            assertEquals(expected.toString(), comments.toString(), "No comment for Q1");
        }
    }

    @Test
    @DisplayName("all comments written by a designated nickname")
    void findByNickname() {
        /*all comments of Park */
        {
            //prepare input data
            String nickname = "Kim";
            //execution
            List<Comment> comments = commentRepository.findByNickname(nickname);
            //expectation
            Comment a = new Comment(1L, new Article(4L, "Soul food?", "go comment"), nickname, "Chicken");
            List<Comment> expected = Arrays.asList(a);
            //check
            assertEquals(expected.toString(), comments.toString());
        }
    }
}