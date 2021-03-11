package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.request.Request;
import com.realestate01.springboot.domain.request.RequestRepository;
import com.realestate01.springboot.web.dto.RequestListResponseDto;
import com.realestate01.springboot.web.dto.RequestResponseDto;
import com.realestate01.springboot.web.dto.RequestSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
@Service
public class RequestService {
    private final RequestRepository requestRepository;

    public Long save(RequestSaveRequestDto requestDto){
        return requestRepository.save(requestDto.toEntity()).getId();
    }

    public RequestResponseDto findById(Long id){
        Request entity = requestRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 의뢰가 없습니다. id="+id));
        return new RequestResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<RequestListResponseDto> findByDesc(Pageable pageable, String a){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1
                , pageable.getPageSize());

        if(a == "all") return requestRepository.findAllDesc(pageable).map(RequestListResponseDto::new);
        else return requestRepository.findTradeDesc(pageable,a).map(RequestListResponseDto::new);
    }

    @Transactional
    public void delete(Long id){
        Request request = requestRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 의뢰가 없습니다. id="+id));
        requestRepository.delete(request);
    }
}

