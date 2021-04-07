package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.question.Question;
import com.realestate01.springboot.domain.question.QuestionRepository;
import com.realestate01.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Transactional
    public Long save(QuestionSaveRequestDto requestDto) {
        return questionRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, QuestionUpdateRequestDto requestDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        question.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public Long updateStatement(Long id, QuestionStatementUpdateDto requestDto) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        question.updateStatement(requestDto.getStatement());

        return id;
    }


    @Transactional
    public void delete (Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        questionRepository.delete(question);
    }

    @Transactional(readOnly = true)
    public QuestionResponseDto findById(Long id) {
        Question entity = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new QuestionResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<QuestionListResponseDto> findAllDesc(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1
                , pageable.getPageSize());
        return questionRepository.findAllDesc(pageable)
                .map(QuestionListResponseDto::new);

    }
}
