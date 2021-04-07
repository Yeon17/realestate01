package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.answer.Answer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerResponseDto {
    private Long id;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;

    public AnswerResponseDto(Answer entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
