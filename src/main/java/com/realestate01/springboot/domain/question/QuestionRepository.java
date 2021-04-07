package com.realestate01.springboot.domain.question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q ORDER BY q.id DESC")
    Page<Question> findAllDesc(Pageable pageable);
}
