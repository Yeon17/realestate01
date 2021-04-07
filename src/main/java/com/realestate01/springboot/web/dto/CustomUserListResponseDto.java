package com.realestate01.springboot.web.dto;
import com.realestate01.springboot.domain.user.Role;
import com.realestate01.springboot.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomUserListResponseDto {
    private Long id;
    private String email;
    private String name;
    private Role role;
    private LocalDateTime createdDate;

    public CustomUserListResponseDto(User entity){
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.createdDate = entity.getCreatedDate();
        this.role = entity.getRole();
    }
}
