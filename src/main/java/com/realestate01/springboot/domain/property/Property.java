package com.realestate01.springboot.domain.property;

import com.realestate01.springboot.domain.BaseTimeEntity;
import com.realestate01.springboot.domain.cart.CartProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Property extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String apart;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String trade;

    @Column(nullable = false)
    private String price;

    private String admin_expense;

    private String include;

    private String floor_current;

    private String floor_total;

    private String floor_height;

    private String room;

    private String bathroom;

    private String actual_area;

    private String actual_area_py;

    private String supply_area;

    private String supply_area_py;

    private String park;

    private String park_all;

    private String direction;

    private String elevator;

    private String enter_date;

    private String heat;

    private String household;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String image;

    private String represent_img;

    private Long uid;

    @OneToMany(mappedBy = "property", fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<CartProduct>();

    @Builder
    public Property(String apart, String address, String trade, String price, String admin_expense, String include,
                    String floor_current, String floor_total, String floor_height, String room, String bathroom,
                    String actual_area, String actual_area_py, String supply_area, String supply_area_py, String park,
                    String park_all, String direction, String elevator, String enter_date, String heat,
                    String household, String title, String content, String image, String represent_img, Long uid){

        this.apart = apart;
        this.address = address;
        this.trade = trade;
        this.price = price;
        this.admin_expense = admin_expense;
        this.include = include;
        this.floor_current = floor_current;
        this.floor_total = floor_total;
        this.floor_height = floor_height;
        this.room = room;
        this.bathroom = bathroom;
        this.actual_area = actual_area;
        this.actual_area_py = actual_area_py;
        this.supply_area = supply_area;
        this.supply_area_py = supply_area_py;
        this.park = park;
        this.park_all = park_all;
        this.direction = direction;
        this.elevator = elevator;
        this.enter_date = enter_date;
        this.heat = heat;
        this.household = household;
        this.title = title;
        this.content = content;
        this.image = image;
        this.represent_img = represent_img;
        this.uid = uid;
    }

    public void update(String apart, String address, String trade, String price, String admin_expense, String include,
                       String floor_current, String floor_total, String floor_height, String room, String bathroom,
                       String actual_area, String actual_area_py, String supply_area, String supply_area_py, String park,
                       String park_all, String direction, String elevator, String enter_date, String heat,
                       String household, String title, String content, String image, String represent_img) {

        this.apart = apart;
        this.address = address;
        this.trade = trade;
        this.price = price;
        this.admin_expense = admin_expense;
        this.include = include;
        this.floor_current = floor_current;
        this.floor_total = floor_total;
        this.floor_height = floor_height;
        this.room = room;
        this.bathroom = bathroom;
        this.actual_area = actual_area;
        this.actual_area_py = actual_area_py;
        this.supply_area = supply_area;
        this.supply_area_py = supply_area_py;
        this.park = park;
        this.park_all = park_all;
        this.direction = direction;
        this.elevator = elevator;
        this.enter_date = enter_date;
        this.heat = heat;
        this.household = household;
        this.title = title;
        this.content = content;
        this.image = image;
        this.represent_img = represent_img;
    }
}
