package com.realestate01.springboot.web;

import com.realestate01.springboot.service.AnswerService;
import com.realestate01.springboot.web.dto.AnswerSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AnswerApiController {
    private final AnswerService answerService;

    @PostMapping("/api/v1/answer")
    public Long save(@RequestBody AnswerSaveRequestDto requestDto) {
        return answerService.save(requestDto);
    }

    @PutMapping("/api/v1/answer/{id}")
    public Long update(@PathVariable Long id, @RequestBody String content) {

        return answerService.update(id, content);
    }

    @DeleteMapping("/api/v1/answer/{id}")
    public Long delete(@PathVariable Long id) {
        answerService.delete(id);
        return id;
    }
}
