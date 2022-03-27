package com.example.springbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB can recognize the object(make a table for the object class)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Article {

    @Id //unique key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB make id automatically
    private Long id;

    @Column //It can be linked to the table of DB
    private String title;

    @Column
    private String content;

}
