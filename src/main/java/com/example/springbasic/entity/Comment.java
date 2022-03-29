package com.example.springbasic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //many comment entities are linked to an article
    @JoinColumn(name = "article_id") //linked object info
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;
}
