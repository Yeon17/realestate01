package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.cart.CartProduct;
import lombok.Getter;

@Getter
public class CartProductListResponseDto {
    private Long id;
    private String represent_img;
    private String title;
    private String price;

    public CartProductListResponseDto(CartProduct cartProduct){
        this.id = cartProduct.getProperty().getId();
        this.represent_img = cartProduct.getProperty().getRepresent_img();
        this.title = cartProduct.getProperty().getTitle();
        this.price = cartProduct.getProperty().getPrice();
    }
}
