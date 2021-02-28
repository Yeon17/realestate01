package com.realestate01.springboot.domain.property;

import com.realestate01.springboot.domain.request.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p FROM Property p ORDER BY p.id DESC")
    Page<Property> findAllDesc(Pageable pageable);

    @Query("SELECT p FROM Property p WHERE p.apart IN (:apt) ORDER BY p.id DESC")
    Page<Property> findApartDesc(Pageable pageable, @Param("apt") String apt);
}
