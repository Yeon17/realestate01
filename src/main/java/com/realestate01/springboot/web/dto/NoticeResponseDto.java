package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.notice.Notice;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;
    private Long uid;
    private LocalDateTime modifiedDate;

    public NoticeResponseDto(Notice entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.uid = entity.getUid();
        this.modifiedDate = entity.getModifiedDate();
    }
}
