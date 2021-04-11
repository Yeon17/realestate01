package com.realestate01.springboot.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

    @Query("SELECT cp FROM CartProduct cp JOIN User u ON u.cart.id = cp.cart.id WHERE cp.property.id = (:pid) AND u.id = (:uid)")
    CartProduct findByPid(@Param("uid")Long uid, @Param("pid")Long pid);

    @Transactional
    @Modifying
    @Query("DELETE FROM CartProduct cp WHERE cp.property.id = :pid")
    void deleteByPid(@Param("pid")Long pid);

}
