package com.realestate01.springboot.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository  extends JpaRepository<Notice, Long> {
    @Query("SELECT n FROM Notice n ORDER BY n.id DESC")
    Page<Notice> findAllDesc(Pageable pageable);
}
