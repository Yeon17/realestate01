package com.realestate01.springboot.domain.cart;

import com.realestate01.springboot.domain.property.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
/*
    @Query("SELECT cp FROM Cart c Join CartProduct cp ON c.id = cp.cart.id WHERE c.user.id = :uid")
    List<CartProduct> findCartList(@Param("uid")Long uid);

    @Query("SELECT cp.property FROM Cart c Join CartProduct cp ON c.id = cp.cart.id WHERE c.user.id = :uid AND cp.property.id = :pid")
    Property findCartProperty(@Param("uid")Long uid, @Param("pid")Long pid);

    @Query("SELECT c FROM Cart c WHERE c.user.id = :uid")
    Cart findByUid(@Param("uid")Long uid);

 */

}
