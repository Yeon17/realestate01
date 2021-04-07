package com.realestate01.springboot.domain.question;

import com.realestate01.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private Long uid;

    private String statement; //답변여부

    @Builder
    public Question(String title, String content, String author, String statement, Long uid) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.statement = statement;
        this.uid = uid;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateStatement(String statement){
        this.statement = statement;
    }
}
