package com.realestate01.springboot.web;

import com.realestate01.springboot.service.CartService;
import com.realestate01.springboot.web.dto.CartSaveOrDeleteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CartApiController {
    final private CartService cartService;

    @PostMapping("/api/v1/cart")
    public Long saveOrUpdate(@RequestBody CartSaveOrDeleteDto cartSaveOrDeleteDto){
        return cartService.saveOrUpdate(cartSaveOrDeleteDto);
    }

    @DeleteMapping("/api/v1/cart")
    public Long delete(@RequestBody CartSaveOrDeleteDto cartSaveOrDeleteDto){
        return cartService.delete(cartSaveOrDeleteDto);
    }
}
