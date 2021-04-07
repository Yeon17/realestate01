package com.realestate01.springboot.domain.notice;

import com.realestate01.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    private Long uid;

    @Builder
    public Notice(String title, String content, String author, Long uid) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.uid = uid;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
