package com.realestate01.springboot.web;

import com.realestate01.springboot.service.PropertyService;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.PropertyUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PropertyApiController {

    private final PropertyService propertyService;

    @PostMapping("/api/v1/property")
    public Long save(@RequestBody PropertySaveRequestDto requestDto){
        return propertyService.save(requestDto);
    }

    @PutMapping("/api/v1/property/{id}")
    public Long update(@PathVariable Long id, @RequestBody PropertyUpdateRequestDto requestDto) {
        return propertyService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/property/{id}")
    public Long delete(@PathVariable Long id) {
        propertyService.delete(id);
        return id;
    }

}
