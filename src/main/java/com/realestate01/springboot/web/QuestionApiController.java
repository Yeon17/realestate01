package com.realestate01.springboot.web;

import com.realestate01.springboot.domain.question.Question;
import com.realestate01.springboot.service.QuestionService;
import com.realestate01.springboot.web.dto.QuestionResponseDto;
import com.realestate01.springboot.web.dto.QuestionSaveRequestDto;
import com.realestate01.springboot.web.dto.QuestionStatementUpdateDto;
import com.realestate01.springboot.web.dto.QuestionUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionApiController {

    private final QuestionService questionService;

    @PostMapping("/api/v1/question")
    public Long save(@RequestBody QuestionSaveRequestDto requestDto) {

        return questionService.save(requestDto);
    }

    @PutMapping("/api/v1/question/{id}")
    public Long update(@PathVariable Long id, @RequestBody QuestionUpdateRequestDto requestDto) {
        return questionService.update(id, requestDto);
    }

    @PutMapping("/api/v1/question/statement/{id}")
    public Long updateStatement(@PathVariable Long id, @RequestBody QuestionStatementUpdateDto requestDto){
        return questionService.updateStatement(id, requestDto);
    }

    @DeleteMapping("/api/v1/question/{id}")
    public Long delete(@PathVariable Long id) {
        questionService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/question/{id}")
    public QuestionResponseDto findById(@PathVariable Long id) {
        return questionService.findById(id);
    }
}
