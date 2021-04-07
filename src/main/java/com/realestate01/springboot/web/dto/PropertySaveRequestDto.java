package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.property.Property;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PropertySaveRequestDto {

    private String apart;

    private String address;

    private String trade;

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

    private String title;

    private String content;

    private String image;

    private String represent_img;

    private Long uid;

    @Builder
    public PropertySaveRequestDto(String apart, String address, String trade, String price, String admin_expense, String include,
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

    public Property toEntity(){

        return Property.builder()
                .apart(apart)
                .address(address)
                .trade(trade)
                .price(price)
                .admin_expense(admin_expense)
                .include(include)
                .floor_current(floor_current)
                .floor_total(floor_total)
                .floor_height(floor_height)
                .room(room)
                .bathroom(bathroom)
                .actual_area(actual_area)
                .actual_area_py(actual_area_py)
                .supply_area(supply_area)
                .supply_area_py(supply_area_py)
                .park(park)
                .park_all(park_all)
                .direction(direction)
                .elevator(elevator)
                .enter_date(enter_date)
                .heat(heat)
                .household(household)
                .title(title)
                .content(content)
                .image(image)
                .represent_img(represent_img)
                .uid(uid)
                .build();

    }

}
