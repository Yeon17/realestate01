package com.realestate01.springboot.web;

import com.realestate01.springboot.service.RequestService;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.RequestResponseDto;
import com.realestate01.springboot.web.dto.RequestSaveRequestDto;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RequestApiController {
    private final RequestService requestService;

    @PostMapping("/api/v1/request")
    public Long save(@RequestBody RequestSaveRequestDto requestDto){
        return requestService.save(requestDto);
    }

    @GetMapping("/api/v1/request/{id}")
    public RequestResponseDto findById (@PathVariable Long id){
        return requestService.findById(id);
    }

    @DeleteMapping("/api/v1/request/{id}")
    public Long delete(@PathVariable Long id){
        requestService.delete(id);
        return id;
    }

}