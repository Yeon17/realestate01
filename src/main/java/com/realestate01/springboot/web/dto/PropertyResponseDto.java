package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.property.Property;
import lombok.Getter;

@Getter
public class PropertyResponseDto {
    private long id;

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

    private Long uid;

    public PropertyResponseDto(Property entity){
        this.id = entity.getId();
        this.apart = entity.getApart();
        this.address = entity.getAddress();
        this.trade = entity.getTrade();
        this.price = entity.getPrice();
        this.admin_expense = entity.getAdmin_expense();
        this.include = entity.getInclude();
        this.floor_current = entity.getFloor_current();
        this.floor_total = entity.getFloor_total();
        this.floor_height = entity.getFloor_height();
        this.room = entity.getRoom();
        this.bathroom = entity.getBathroom();
        this.actual_area = entity.getActual_area();
        this.actual_area_py = entity.getActual_area_py();
        this.supply_area = entity.getSupply_area();
        this.supply_area_py = entity.getSupply_area_py();
        this.park = entity.getPark();
        this.park_all = entity.getPark_all();
        this.direction = entity.getDirection();
        this.elevator = entity.getElevator();
        this.enter_date = entity.getEnter_date();
        this.heat = entity.getHeat();
        this.household = entity.getHousehold();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.image = entity.getImage();
        this.uid = entity.getUid();
    }
}
