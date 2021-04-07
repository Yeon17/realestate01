package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.answer.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerSaveRequestDto {
    private Long qid;
    private String content;
    private String author;

    @Builder
    public AnswerSaveRequestDto(Long qid, String content, String author) {
        this.qid = qid;
        this.content = content;
        this.author = author;
    }

    public Answer toEntity() {
        return Answer.builder()
                .qid(qid)
                .content(content)
                .author(author)
                .build();
    }

}
