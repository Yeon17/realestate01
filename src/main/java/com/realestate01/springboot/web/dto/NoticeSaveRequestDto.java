package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Long uid;

    @Builder
    public NoticeSaveRequestDto(String title, String content, String author, Long uid) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.uid = uid;
    }

    public Notice toEntity() {
        return Notice.builder()
                .title(title)
                .content(content)
                .author(author)
                .uid(uid)
                .build();
    }

}
