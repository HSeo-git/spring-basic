package com.example.springbasic.service;

import com.example.springbasic.dto.ArticleForm;
import com.example.springbasic.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //this class is tested linked to SpringBoot
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        //expectation
        Article a = new Article(1L, "abc", "def");
        Article b = new Article(2L, "bca", "def");
        Article c = new Article(3L, "cba", "def");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        //actual
        List<Article> articles = articleService.index();

        //comparison
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_success_id_existed() {
        //expect
        Long id = 1L;
        Article expected = new Article(id, "abc", "def");

        //actual
        Article article = articleService.show(id);

        //comparison
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail_not_existed() {
        //expect
        Long id = -1L;
        Article expected = null;

        //actual
        Article article = articleService.show(id);

        //comparison
        assertEquals(expected, article); //when null, toString() is not working

    }

    @Test
    @Transactional
    void create_success_title_and_content_dto() {
        //expect
        String title = "test";
        String content = "test-ing";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        //actual
        Article article = articleService.create(dto);

        //comparison
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void create_fail_dto_including_id() {
        //expect
        String title = "test";
        String content = "test-ing";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        //actual
        Article article = articleService.create(dto);

        //comparison
        assertEquals(expected, article);

    }

}