package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.question.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String statement;
    private LocalDateTime modifiedDate;

    public QuestionListResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.statement = entity.getStatement();
        this.modifiedDate = entity.getModifiedDate();
    }
}
