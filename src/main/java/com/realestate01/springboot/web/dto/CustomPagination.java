package com.realestate01.springboot.web.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class CustomPagination {
    int total;
    int page_number;
    int start;
    int end;
    int[] view_numbers;
    boolean check_pre;
    boolean check_next;

    public CustomPagination(Page request){
        total = request.getTotalPages();
        page_number = request.getNumber()+1;

        int k = (page_number-1)/5;
        start = 5*k + 1;
        end = start+5;

        if(total == 0) end = 2;
        else if(end > total+1) end = total+1;

        view_numbers = new int[end-start];
        for(int i = 0; i < end-start; i++) view_numbers[i] = start + i;

        if(page_number < 6) check_pre = false;
        else check_pre = true;

        if(start+5 > total) check_next = false;
        else check_next = true;
    }

}
