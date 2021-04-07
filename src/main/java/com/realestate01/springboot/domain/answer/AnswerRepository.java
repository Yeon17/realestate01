package com.realestate01.springboot.domain.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Nullable
    @Query("SELECT a FROM Answer a WHERE a.qid = :qid")
    Optional<Answer> findByQid(@Param("qid") Long id);
}
