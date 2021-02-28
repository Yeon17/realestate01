package com.realestate01.springboot.web;

import com.realestate01.springboot.service.PropertyService;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PropertyApiController {

    private final PropertyService propertyService;

    @PostMapping("/api/v1/property")
    public Long save(@RequestBody PropertySaveRequestDto requestDto){
        return propertyService.save(requestDto);
    }


}
