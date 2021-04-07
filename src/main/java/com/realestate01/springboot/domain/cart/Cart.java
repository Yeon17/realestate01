package com.realestate01.springboot.domain.cart;

import com.realestate01.springboot.domain.user.User;
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

    @OneToMany(cascade = CascadeType.ALL)
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
