package com.realestate01.springboot.domain.cart;

import com.realestate01.springboot.domain.property.Property;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROPERTY_ID")
    private Property property;

    @Builder
    public CartProduct(Cart cart, Property property){
        this.cart = cart;
        this.property = property;
    }
}
