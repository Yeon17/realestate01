package com.realestate01.springboot.web.dto;

import com.realestate01.springboot.domain.request.Request;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestSaveRequestDto {
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
    public RequestSaveRequestDto(String trade, String addressKindU, String addressKindD, String transaction,
                                 String price, String date, String visit, String name, String phone, String message) {

        this.trade= trade;
        this.addressKindU = addressKindU;
        this.addressKindD = addressKindD;
        this.transaction = transaction;
        this.price = price;
        this.date = date;
        this.visit = visit;
        this.name = name;
        this.phone = phone;
        this.message = message;
    }

    public Request toEntity(){
        return Request.builder()
                .trade(trade)
                .addressKindU(addressKindU)
                .addressKindD(addressKindD)
                .transaction(transaction)
                .price(price)
                .date(date)
                .visit(visit)
                .name(name)
                .phone(phone)
                .message(message)
                .build();
    }
}
