package com.example.springbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //DB can recognize the object
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Article {

    @Id //unique key
    @GeneratedValue //1, 2, 3,... make by itself
    private Long id;

    @Column //It can be linked to the table of DB
    private String title;

    @Column
    private String content;

}
