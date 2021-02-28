package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.request.Request;
import lombok.Getter;

@Getter
public class RequestResponseDto {
    private Long id;

    private String trade;

    private String addressKindU;

    private String addressKindD;

    private String transaction;

    private String price;

    private String date;

    private String visit;

    private String name;

    private String phone;

    private String message;

    public RequestResponseDto(Request entity){
        this.id = entity.getId();

        this.trade = entity.getTrade();

        this.addressKindU = entity.getAddressKindU();

        this.addressKindD = entity.getAddressKindD();

        this.transaction = entity.getTransaction();

        this.price = entity.getPrice();

        this.date = entity.getDate();

        this.visit = entity.getVisit();

        this.name = entity.getName();

        this.phone = entity.getPhone();

        this.message = entity.getMessage();
    }

}
