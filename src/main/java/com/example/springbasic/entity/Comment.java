package com.example.springbasic.entity;

import com.example.springbasic.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        //deal with exceptions
        if (dto.getId() != null)
            throw new IllegalArgumentException("Fail to create! comment Id already exists");
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("Fail to create! article Id Error");
        //create entity and response
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //exception
        if (this.id != dto.getId())
            throw new IllegalArgumentException("Fail to update! wrong ID");
        //update
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
