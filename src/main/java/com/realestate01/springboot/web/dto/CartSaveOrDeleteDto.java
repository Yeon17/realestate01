package com.realestate01.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartSaveOrDeleteDto {
    private Long uid;
    private Long pid;


    @Builder
    public CartSaveOrDeleteDto(Long uid, Long pid){
        this.uid = uid;
        this.pid = pid;

    }

}
