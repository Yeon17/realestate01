package com.realestate01.springboot.domain.request;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT q FROM Request q ORDER BY q.id DESC")
    Page<Request> findAllDesc(Pageable pageable);

    @Query("SELECT q FROM Request q WHERE q.trade IN (:tra) ORDER BY q.id DESC")
    Page<Request> findTradeDesc(Pageable pageable, @Param("tra") String tra);

}
