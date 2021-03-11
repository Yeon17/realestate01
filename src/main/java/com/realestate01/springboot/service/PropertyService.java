package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.web.dto.PropertyListResponseDto;
import com.realestate01.springboot.web.dto.PropertyResponseDto;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.PropertyUpdateRequestDto;
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

    public PropertyResponseDto findById(Long id) {
        Property entity = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 매물이 없습니다. id=" + id));

        return new PropertyResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, PropertyUpdateRequestDto requestDto) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 매물이 없습니다. id=" + id));

        property.update(requestDto.getApart(), requestDto.getAddress(), requestDto.getTrade(),
                requestDto.getPrice(), requestDto.getAdmin_expense(), requestDto.getInclude(),
                requestDto.getFloor_current(), requestDto.getFloor_total(),
                requestDto.getFloor_height(), requestDto.getRoom(), requestDto.getBathroom(),
                requestDto.getActual_area(), requestDto.getActual_area_py(),
                requestDto.getSupply_area(), requestDto.getSupply_area_py(), requestDto.getPark(),
                requestDto.getPark_all(), requestDto.getDirection(), requestDto.getElevator(),
                requestDto.getEnter_date(), requestDto.getHeat(), requestDto.getHousehold(),
                requestDto.getTitle(), requestDto.getContent(), requestDto.getImage(), requestDto.getRepresent_img());

        return id;
    }

    @Transactional
    public void delete (Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 매물이 없습니다. id=" + id));

        propertyRepository.delete(property);
    }
}
