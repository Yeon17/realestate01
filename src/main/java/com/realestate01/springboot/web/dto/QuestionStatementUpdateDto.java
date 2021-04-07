package com.realestate01.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionStatementUpdateDto {
    private String statement;

    @Builder
    public QuestionStatementUpdateDto(String statement){
        this.statement = statement;
    }

}
