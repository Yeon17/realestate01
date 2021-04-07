package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.question.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Long uid;
    private LocalDateTime modifiedDate;

    public QuestionResponseDto(Question entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.uid = entity.getUid();
        this.modifiedDate = entity.getModifiedDate();
    }
}
