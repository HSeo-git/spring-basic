package com.example.springbasic.controller;

import com.example.springbasic.dto.ArticleForm;
import com.example.springbasic.dto.CommentDto;
import com.example.springbasic.entity.Article;
import com.example.springbasic.entity.Comment;
import com.example.springbasic.repository.ArticleRepository;
import com.example.springbasic.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j //annotation for logging
public class ArticleController {

    @Autowired //automatically linked to the object which Spring Boot has already prepared
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        //System.out.println(form.toString() ); --> Logging
        log.info(form.toString());

        //1. change DTO into Entity
        Article article = form.toEntity();
        //System.out.println(article.toString());
        log.info(article.toString());

        //2. let repository save DTO in server
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id ="+id);

        //1. get data with id
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        //2. apply the data to the model
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos",commentDtos);

        //3. set the page
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. get all articles
        List<Article> articleEntityList = articleRepository.findAll();
        //2. send to view a bunch of articles
        model.addAttribute("articleList", articleEntityList);
        //3. set view page
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit (@PathVariable Long id, Model model) { //PathVariable is a tool for get a param from the address
        //get data supposed to be edited
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //register the data to model
        model.addAttribute("article", articleEntity);
        return "articles/edit"; //set view page
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        //1. dto ->entity
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. entity -> db
        //2-1. get the original data from db
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. if the original data exist, revise/update the original data
        if ((target != null)) {
            articleRepository.save(articleEntity);
        }
        //3. redirect to the result page
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {

        //1. get the subject to be deleted
        Article target = articleRepository.findById(id).orElse(null);
        //2. delete the subject
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "delete completed");
        }
        //3. redirect to the final page
        return "redirect:/articles";
    }
}
