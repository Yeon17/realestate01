package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.cart.Cart;
import com.realestate01.springboot.domain.cart.CartProduct;
import com.realestate01.springboot.domain.cart.CartProductRepository;
import com.realestate01.springboot.domain.cart.CartRepository;
import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.domain.user.User;
import com.realestate01.springboot.domain.user.UserRepository;
import com.realestate01.springboot.web.dto.CartProductListResponseDto;
import com.realestate01.springboot.web.dto.CartSaveOrDeleteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PropertyRepository propertyRepository;
    private final CartProductRepository cartProductRepository;

    @Transactional
    public Long saveOrUpdate(CartSaveOrDeleteDto requestDto) {

        User user = userRepository.findById(requestDto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        if(user.getCart() == null) {
            user.setCart(cartRepository.save(new Cart()));
        }

        Property property = propertyRepository.findById(requestDto.getPid())
                .orElseThrow(() -> new IllegalArgumentException("해당 매물이 없습니다."));

        CartProduct cartProduct = cartProductRepository.save(new CartProduct(user.getCart(), property));
        user.addCartProduct(cartProduct);

        return user.getCart().getId();

    }

    @Transactional
    public Long delete(CartSaveOrDeleteDto requestDto){
        User user = userRepository.findById(requestDto.getUid())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        CartProduct product = cartProductRepository.findByPid(requestDto.getUid(), requestDto.getPid());
        user.deleteCartProduct(product);
        cartProductRepository.delete(product); //삭제 하면 이 매물이 2명 이상의 유저 카트에 담겨있다면 어쩔?

        return user.getCart().getId();
    }

    @Transactional
    public void deleteByPid(Long id){
        cartProductRepository.deleteByPid(id);
    }

    @Transactional(readOnly = true)
    public List<CartProductListResponseDto> findUserCart(Long id) {
        return userRepository.findCartList(id).stream()
                .map(CartProductListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public boolean checkScrap(Long uid, Long pid){
        if(userRepository.findCartProperty(uid, pid) != null) return true;
        else return false;
    }
}
