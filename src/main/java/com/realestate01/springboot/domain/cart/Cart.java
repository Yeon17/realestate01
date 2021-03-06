package com.realestate01.springboot.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> property = new ArrayList<>();

    @Builder
    public Cart(CartProduct property){

        this.property.add(property);
    }



    public void update(CartProduct property){
        this.property.add(property);

    }

    public void propertyDelete(CartProduct property){
        this.property.remove(property);
    }

}
