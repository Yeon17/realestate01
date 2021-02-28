package com.realestate01.springboot.web;

import com.realestate01.springboot.service.PropertyService;
import com.realestate01.springboot.service.RequestService;
import com.realestate01.springboot.web.dto.CustomPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final RequestService requestService;
    private final PropertyService propertyService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/request/buy")
    public String buy(){
        return "buy";
    }

    @GetMapping("/request/sell")
    public String sell(){
        return "sell";
    }

    @GetMapping("/administer")
    public String administer(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page request = requestService.findByDesc(pageable,"all");
        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "administer";
    }

    @GetMapping("/administer/buy")
    public String administer_buy(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page request = requestService.findByDesc(pageable, "매수");

        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "administer";
    }

    @GetMapping("/administer/sell")
    public String administer_sell(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page request = requestService.findByDesc(pageable,"매도");
        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "administer";
    }

    @GetMapping("/enroll-property")
    public String enroll_property(){
        return "enroll-property";
    }



    @GetMapping("/lookup-property")
    public String lookup_property(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "all");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }

}
