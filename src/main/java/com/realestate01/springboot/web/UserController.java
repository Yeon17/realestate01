package com.realestate01.springboot.web;

import com.realestate01.springboot.service.UserService;
import com.realestate01.springboot.web.dto.CustomUserDto;
import com.realestate01.springboot.web.dto.CustomUserUpdateDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/api/v0/user")
    public Long signup(@RequestBody CustomUserDto dto) { // 회원 추가

        return userService.save(dto);
    }

    @PutMapping("/api/v1/user/{uid}")
    public Long edit_user(@PathVariable Long uid, @RequestBody CustomUserUpdateDto dto){
        return userService.update(uid, dto);
    }

    @DeleteMapping("/api/v1/user/{uid}")
    public Map<Object, Object> delete(@PathVariable Long uid,@RequestBody String password){

        Boolean check = userService.delete(uid, password);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("check", check);

        return map;
    }

    @DeleteMapping("/api/v1/oauth_user/{uid}")
    public Long oauth_delete(@PathVariable Long uid){
        userService.oauth_delete(uid);
        return uid;
    }

    @DeleteMapping("/administer/user/{uid}")
    public Long member_delete(@PathVariable Long uid){
        userService.member_delete(uid);
        return uid;
    }

    @PostMapping("/api/v0/doubleCheck")
    public Map<Object, Object> email_check(@RequestBody String email) {
        boolean check = userService.id_check(email);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("check", check);

        return map;
    }
}
