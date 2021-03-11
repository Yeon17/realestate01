package com.realestate01.springboot.web;

import com.realestate01.springboot.service.*;
import com.realestate01.springboot.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final RequestService requestService;
    private final PropertyService propertyService;
    private final NoticeService noticeService;
    private final QuestionService questionService;
    private final AnswerService answerService;

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

    @GetMapping("/lookup-property/park-xi")
    public String lookup_property_park_xi(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "동천파크자이");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }

    @GetMapping("/lookup-property/eastpole")
    public String lookup_property_eastpole(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "더샵 동천 이스트포레");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }

    @GetMapping("/lookup-property/samsung4")
    public String lookup_property_samsung4(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "수지 삼성4차");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }

    @GetMapping("/lookup-property/eastpark")
    public String lookup_property_eastpark(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "수지 래미안이스트파크");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }

    @GetMapping("/lookup-property/puleujio")
    public String lookup_property_puleujio(Model model, @PageableDefault(size = 8) Pageable pageable){

        Page property = propertyService.findByDesc(pageable, "수지 파크푸르지오");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "lookup-property";
    }



    @GetMapping("/property-detail/{id}")
    public String property_detail(@PathVariable Long id, Model model){
        PropertyResponseDto responseDto = propertyService.findById(id);
        PropertyResponseDto dto = propertyService.findById(id);

        String image = dto.getImage();
        String[] arr = null;
        int[] n = null;

        if(image != null){
            arr = image.split(";");
            n = new int[arr.length];
            for(int i = 0; i < arr.length; i++){
                n[i] = i;
            }
        }

        model.addAttribute("property", dto);
        model.addAttribute("img", n);
        model.addAttribute("img_arr",arr);

        return "property-detail";
    }

    @GetMapping("/property/update/{id}")
    public String propertyUpdate(@PathVariable Long id, Model model) {
        PropertyResponseDto dto = propertyService.findById(id);
        String image = dto.getImage();
        String[] arr = null;

        if(image != null){
            arr = image.split(";");
        }

        model.addAttribute("property", dto);
        model.addAttribute("img_arr",arr);

        return "update-property";
    }

    @GetMapping("/notice")
    public String notice(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page notice = noticeService.findAllDesc(pageable);
        CustomPagination pagination = new CustomPagination(notice);

        model.addAttribute("notice", notice);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "notice";
    }

    @GetMapping("/enroll-notice")
    public String enroll_notice(){
        return "enroll-notice";
    }

    @GetMapping("/notice-detail/{id}")
    public String notice_detail(@PathVariable Long id, Model model){
        NoticeResponseDto dto = noticeService.findById(id);

        model.addAttribute("notice",dto);

        return "notice-detail";
    }

    @GetMapping("/notice/update/{id}")
    public String update_notice(@PathVariable Long id, Model model){
        NoticeResponseDto dto = noticeService.findById(id);

        model.addAttribute("notice", dto);

        return "update-notice";
    }

    @GetMapping("/question")
    public String question(Model model, @PageableDefault(size = 5) Pageable pageable){
        Page question = questionService.findAllDesc(pageable);
        CustomPagination pagination = new CustomPagination(question);

        model.addAttribute("question", question);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        return "question";
    }

    @GetMapping("/enroll-question")
    public String enroll_question(){
        return "enroll-question";
    }

    @GetMapping("/question/update/{id}")
    public String update_question(@PathVariable Long id, Model model){
        QuestionResponseDto dto = questionService.findById(id);

        model.addAttribute("question", dto);

        return "update-question";
    }

    @GetMapping("/question-detail/{id}")
    public String question_detail(@PathVariable Long id, Model model){
        QuestionResponseDto dto = questionService.findById(id);
        AnswerResponseDto ans_dto = answerService.findByQid(id);

        model.addAttribute("question",dto);
        model.addAttribute("answer", ans_dto);

        return "question-detail";
    }
}
