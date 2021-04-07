package com.realestate01.springboot.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Query("SELECT cp FROM CartProduct cp WHERE cp.property.id = (:pid)")
    CartProduct findByPid(@Param("pid")Long pid);

}
