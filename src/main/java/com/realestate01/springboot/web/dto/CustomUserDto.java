package com.realestate01.springboot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomUserDto {
    private String email;
    private String password;
    private String name;

    @Builder
    public CustomUserDto(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

}
