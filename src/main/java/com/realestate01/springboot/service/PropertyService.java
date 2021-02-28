package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.web.dto.PropertyListResponseDto;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.RequestListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public Long save(PropertySaveRequestDto requestDto){
        return propertyRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public Page<PropertyListResponseDto> findByDesc(Pageable pageable, String a){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1
                , pageable.getPageSize());

        if(a == "all") return propertyRepository.findAllDesc(pageable).map(PropertyListResponseDto::new);
        else return propertyRepository.findApartDesc(pageable,a).map(PropertyListResponseDto::new);
    }
}
