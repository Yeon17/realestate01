package com.realestate01.springboot.config.auth.dto;

import com.realestate01.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private String role;
    private String password;
    private Boolean oauth;

    public SessionUser(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.role = user.getRoleKey();
        this.oauth = user.getOauth(); // 소셜 로그인 연동 계정
    }

    public SessionUser(Long id, String email, String name, String role, String password, Boolean oauth){
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.password = password;
        this.oauth = oauth;
    }

}
