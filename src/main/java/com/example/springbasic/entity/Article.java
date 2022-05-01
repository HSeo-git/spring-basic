package com.example.springbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB can recognize the object(make a table for the object class)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "article")
public class Article {

    @Id //unique key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB make id automatically
    private Long id;

    @Column //It can be linked to the table of DB
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }

}
