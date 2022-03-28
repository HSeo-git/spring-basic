package com.example.springbasic.service;

import com.example.springbasic.dto.ArticleForm;
import com.example.springbasic.entity.Article;
import com.example.springbasic.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //annotation of service(register service to spring boot)
public class ArticleService {
    @Autowired //DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(long id, ArticleForm dto) {
        //1. make entity for update
        Article article = dto.toEntity();
        log.info("id:{}, article:{}", id, article.toString());
        //2. find the entity
        Article target = articleRepository.findById(id).orElse(null);
        //3. deal with wrong request like no object, different id
        if (target == null || id != article.getId()) {
            //400 wrong request
            log.info("wrong request! id:{}, article:{}", id, article.toString());
            return null;
        }
        //4. update
        target.patch(article); //set patch function Article Entity
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //find the data
        Article target = articleRepository.findById(id).orElse(null);
        //deal with wrong request
        if (target == null) {
            return null;
        }
        //delete the data
        articleRepository.delete(target);
        return target;

    }
    @Transactional //bind the method as a transaction(if the process fails, roll-back happens)
    public List<Article> createArticles(List<ArticleForm> dtos) {
        // change dtos into entities
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // save entities in db
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        // make exception
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("Fail")
        );
        // return result
        return articleList;
    }
}
