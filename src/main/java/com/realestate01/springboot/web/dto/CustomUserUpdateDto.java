package com.realestate01.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserUpdateDto {
    private String password;
    private String name;

    @Builder
    public CustomUserUpdateDto(String password, String name){
        this.password = password;
        this.name = name;
    }

}

