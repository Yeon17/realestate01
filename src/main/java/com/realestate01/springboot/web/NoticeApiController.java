package com.realestate01.springboot.web;

import com.realestate01.springboot.service.NoticeService;
import com.realestate01.springboot.web.dto.NoticeListResponseDto;
import com.realestate01.springboot.web.dto.NoticeResponseDto;
import com.realestate01.springboot.web.dto.NoticeSaveRequestDto;
import com.realestate01.springboot.web.dto.NoticeUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticeApiController {

    private final NoticeService noticeService;

    @PostMapping("/api/v1/notice")
    public Long save(@RequestBody NoticeSaveRequestDto requestDto) {
        return noticeService.save(requestDto);
    }

    @PutMapping("/api/v1/notice/{id}")
    public Long update(@PathVariable Long id, @RequestBody NoticeUpdateRequestDto requestDto) {
        return noticeService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/notice/{id}")
    public Long delete(@PathVariable Long id) {
        noticeService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/notice/{id}")
    public NoticeResponseDto findById(@PathVariable Long id) {
        return noticeService.findById(id);
    }
}
