package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.question.Question;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private String statement;
    private Long uid;

    @Builder
    public QuestionSaveRequestDto(String title, String content, String author, String statement, Long uid) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.statement = statement;
        this.uid = uid;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .author(author)
                .statement(statement)
                .uid(uid)
                .build();
    }

}
