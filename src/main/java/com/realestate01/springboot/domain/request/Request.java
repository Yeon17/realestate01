package com.realestate01.springboot.domain.request;

import com.realestate01.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Request extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public Request(String trade, String addressKindU, String addressKindD, String transaction,
                   String price, String date, String visit, String name, String phone, String message){
        this.trade = trade;
        this.addressKindU = addressKindU;
        this.addressKindD = addressKindD;
        this.transaction = transaction;
        this.price = price;
        this.date = date;
        this.visit = visit;
        this.name = name;
        this.phone = phone;
        this. message = message;
    }
}
