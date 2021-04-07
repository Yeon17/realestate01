package com.realestate01.springboot.domain.user;

import com.realestate01.springboot.domain.cart.Cart;
import com.realestate01.springboot.domain.cart.CartProduct;
import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.web.dto.CustomUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.oauth = true")
    Optional<User> findByEmailOauth(@Param("email")String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.oauth = false")
    Optional<User> findByEmailNotOauth(@Param("email")String email);

    @Query("SELECT cp FROM User u Join CartProduct cp ON u.cart.id = cp.cart.id WHERE u.id = :uid")
    List<CartProduct> findCartList(@Param("uid")Long uid);

    @Query("SELECT cp.property FROM User u Join CartProduct cp ON u.cart.id = cp.cart.id WHERE u.id = :uid AND cp.property.id = :pid")
    Property findCartProperty(@Param("uid")Long uid, @Param("pid")Long pid);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    List<User> findAllDesc();
}
