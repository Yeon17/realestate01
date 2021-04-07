package com.realestate01.springboot.domain.answer;

import com.realestate01.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long qid;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Answer(Long qid, String content, String author) {
        this.qid = qid;
        this.content = content;
        this.author = author;
    }

    public void update(String content) {
        this.content = content;
    }

}
