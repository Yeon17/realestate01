package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.notice.Notice;
import com.realestate01.springboot.domain.notice.NoticeRepository;
import com.realestate01.springboot.web.dto.NoticeListResponseDto;
import com.realestate01.springboot.web.dto.NoticeResponseDto;
import com.realestate01.springboot.web.dto.NoticeSaveRequestDto;
import com.realestate01.springboot.web.dto.NoticeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    @Transactional
    public Long save(NoticeSaveRequestDto requestDto) {
        return noticeRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, NoticeUpdateRequestDto requestDto) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        notice.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        noticeRepository.delete(notice);
    }

    @Transactional(readOnly = true)
    public NoticeResponseDto findById(Long id) {
        Notice entity = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new NoticeResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<NoticeListResponseDto> findAllDesc(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1
                , pageable.getPageSize());
        return noticeRepository.findAllDesc(pageable)
                .map(NoticeListResponseDto::new);
    }
}
