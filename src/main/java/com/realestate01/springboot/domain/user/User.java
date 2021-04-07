package com.realestate01.springboot.domain.user;

import com.realestate01.springboot.domain.BaseTimeEntity;
import com.realestate01.springboot.domain.cart.Cart;
import com.realestate01.springboot.domain.cart.CartProduct;
import com.realestate01.springboot.domain.property.Property;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Boolean oauth = false;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Builder
    public User(String name, String email, String picture, Role role, Boolean oauth) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.oauth = oauth;
    }

    public void setCart(Cart cart){
        this.cart = cart;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public void addCartProduct(CartProduct cartProduct){
        this.cart.update(cartProduct);
    }

    public void deleteCartProduct(CartProduct cartProduct){
        cart.propertyDelete(cartProduct);
    }

    public User(String email, String name, String password, Role role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority(role.getKey()));
        return auth;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }


    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }


    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }


    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
    public void update_name(String name){
        this.name = name;
    }

    public void update_info(String password, String name){
        this.password = password;
        this.name = name;
    }
    public void update_pwd(String password){
        this.password = password;
    }


}