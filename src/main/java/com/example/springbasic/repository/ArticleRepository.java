package com.example.springbasic.repository;

import com.example.springbasic.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> { //Long is the type of representative object

    @Override
    ArrayList<Article> findAll();
}
