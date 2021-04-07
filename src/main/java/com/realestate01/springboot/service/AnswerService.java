package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.answer.Answer;
import com.realestate01.springboot.domain.answer.AnswerRepository;
import com.realestate01.springboot.domain.notice.Notice;
import com.realestate01.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    @Transactional
    public Long save(AnswerSaveRequestDto requestDto) {
        return answerRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, String content) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        answer.update(content);

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        answerRepository.delete(answer);
    }

    @Transactional(readOnly = true)
    public AnswerResponseDto findByQid(Long id) {
        Answer entity = new Answer();
        entity = answerRepository.findByQid(id).orElse(null);
        if(entity == null) return null;
        else return new AnswerResponseDto(entity);
    }

}
