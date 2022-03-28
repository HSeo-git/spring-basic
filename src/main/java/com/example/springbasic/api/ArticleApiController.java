package com.example.springbasic.api;

import com.example.springbasic.dto.ArticleForm;
import com.example.springbasic.entity.Article;
import com.example.springbasic.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    //POST

    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) { //u should add RequestBody Annotation
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable long id, @RequestBody ArticleForm dto) {
        //1. make entity for update
        Article article = dto.toEntity();
        log.info("id:{}, article:{}", id, article.toString());
        //2. find the entity
        Article target = articleRepository.findById(id).orElse(null);
        //3. deal with wrong request like no object, different id
        if (target == null || id != article.getId()) {
            //400 wrong request
            log.info("wrong request! id:{}, article:{}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //4. update and normal response(200)
        target.patch(article); //set patch function Article Entity
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article>  delete(@PathVariable Long id) {
        //find the data
        Article target = articleRepository.findById(id).orElse(null);
        //deal with wrong request
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //delete the data
        articleRepository.delete(target);
        //change the data
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
