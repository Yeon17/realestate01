package com.realestate01.springboot.web;

import com.realestate01.springboot.config.auth.LoginUser;
import com.realestate01.springboot.config.auth.dto.SessionUser;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final RequestService requestService;
    private final PropertyService propertyService;
    private final NoticeService noticeService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user, @PageableDefault(size = 6) Pageable pageable){
        if(user != null){
            model.addAttribute("uid", user.getId());

            boolean isAdmin = false;
            if(user.getRole().equals("ROLE_ADMIN")) isAdmin = true;
            model.addAttribute("admin", isAdmin);
        }
        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "all");

        model.addAttribute("property",property);

        return "index";
    }

    @GetMapping("/request/buy")
    public String buy(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }
        return "request-buy";
    }

    @GetMapping("/request/sell")
    public String sell(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }
        return "request-sell";
    }

    @GetMapping("/administer/request")
    public String confirm_request(Model model, @PageableDefault(size = 5) Pageable pageable, @LoginUser SessionUser user){
        Page<RequestListResponseDto> request = requestService.findByDesc(pageable,"all");
        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "request";
    }

    @GetMapping("/administer/request/buy")
    public String confirm_buy(Model model, @PageableDefault(size = 5) Pageable pageable, @LoginUser SessionUser user){
        Page<RequestListResponseDto> request = requestService.findByDesc(pageable, "매수");

        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "request";
    }

    @GetMapping("/administer/request/sell")
    public String confirm_sell(Model model, @PageableDefault(size = 5) Pageable pageable, @LoginUser SessionUser user){
        Page<RequestListResponseDto> request = requestService.findByDesc(pageable,"매도");
        CustomPagination pagination = new CustomPagination(request);

        model.addAttribute("request", request);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "request";
    }

    @GetMapping("/administer/enroll-property")
    public String enroll_property(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property-enroll";
    }


    @GetMapping("/property")
    public String lookup_property(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "all");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property";
    }

    @GetMapping("/property/park-xi")
    public String lookup_property_park_xi(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "동천파크자이");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property";
    }

    @GetMapping("/property/eastpole")
    public String lookup_property_eastpole(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "더샵 동천 이스트포레");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }
        return "property";
    }

    @GetMapping("/property/samsung4")
    public String lookup_property_samsung4(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "수지 삼성4차");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());


        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property";
    }

    @GetMapping("/property/eastpark")
    public String lookup_property_eastpark(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "수지 래미안이스트파크");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property";
    }

    @GetMapping("/property/puleujio")
    public String lookup_property_puleujio(Model model, @PageableDefault(size = 8) Pageable pageable, @LoginUser SessionUser user){

        Page<PropertyListResponseDto> property = propertyService.findByDesc(pageable, "수지 파크푸르지오");
        CustomPagination pagination = new CustomPagination(property);

        model.addAttribute("property", property);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());


        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property";
    }



    @GetMapping("/property-detail/{id}")
    public String property_detail(@PathVariable Long id, Model model, @LoginUser SessionUser user){
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

        if(user != null){
            model.addAttribute("uid", user.getId());
            model.addAttribute("oauth", user.getOauth());
            boolean scrap = cartService.checkScrap(user.getId(),dto.getId());
            model.addAttribute("scrap",scrap);

            boolean isUser = false; //매물 작성자(관리자)만이 수정, 삭제 가능
            if(dto.getUid().equals(user.getId())) isUser = true;
            model.addAttribute("user", isUser);
        }

        return "property-detail";
    }

    @GetMapping("/administer/update-property/{id}")
    public String propertyUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        PropertyResponseDto dto = propertyService.findById(id);
        String image = dto.getImage();
        String[] arr = null;

        if(image != null){
            arr = image.split(";");
        }

        model.addAttribute("property", dto);
        model.addAttribute("img_arr",arr);

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "property-update";
    }

    @GetMapping("/notice")
    public String notice(Model model, @PageableDefault(size = 5) Pageable pageable, @LoginUser SessionUser user){
        Page<NoticeListResponseDto> notice = noticeService.findAllDesc(pageable);
        CustomPagination pagination = new CustomPagination(notice);

        model.addAttribute("notice", notice);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());

            boolean isAdmin = false;
            if(user.getRole().equals("ROLE_ADMIN")) isAdmin = true;
            model.addAttribute("admin", isAdmin);
        }

        return "notice";
    }

    @GetMapping("/administer/enroll-notice")
    public String enroll_notice(Model model, @LoginUser SessionUser user){

        if(user != null){
            model.addAttribute("uid", user.getId());
            model.addAttribute("username", user.getName());
        }

        return "notice-enroll";
    }

    @GetMapping("/notice-detail/{id}")
    public String notice_detail(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        NoticeResponseDto dto = noticeService.findById(id);

        model.addAttribute("notice",dto);

        if(user != null){
            model.addAttribute("uid", user.getId());

            boolean isUser = false;
            boolean isUserOrAdmin = false;
            if(dto.getUid().equals(user.getId())) isUser = true;
            if(dto.getUid().equals(user.getId()) || user.getRole().equals("ROLE_ADMIN")) isUserOrAdmin = true;
            model.addAttribute("user", isUser);
            model.addAttribute("UserOrAdmin", isUserOrAdmin);
        }

        return "notice-detail";
    }

    @GetMapping("/administer/update-notice/{id}")
    public String update_notice(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        NoticeResponseDto dto = noticeService.findById(id);

        model.addAttribute("notice", dto);

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "notice-update";
    }

    @GetMapping("/question")
    public String question(Model model, @PageableDefault(size = 5) Pageable pageable, @LoginUser SessionUser user){
        Page<QuestionListResponseDto> question = questionService.findAllDesc(pageable);
        CustomPagination pagination = new CustomPagination(question);

        model.addAttribute("question", question);
        model.addAttribute("previous", pagination.getPage_number()-1);
        model.addAttribute("next", pagination.getPage_number()+1);
        model.addAttribute("view_numbers", pagination.getView_numbers());
        model.addAttribute("check_pre", pagination.isCheck_pre());
        model.addAttribute("check_next", pagination.isCheck_next());

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "question";
    }

    @GetMapping("/enroll-question")
    public String enroll_question(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
            model.addAttribute("username",user.getName());
        }

        return "question-enroll";
    }

    @GetMapping("/update-question/{id}")
    public String update_question(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        QuestionResponseDto dto = questionService.findById(id);

        model.addAttribute("question", dto);

        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "question-update";
    }

    @GetMapping("/question-detail/{id}")
    public String question_detail(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        QuestionResponseDto dto = questionService.findById(id);
        AnswerResponseDto ans_dto = answerService.findByQid(id);

        model.addAttribute("question",dto);
        model.addAttribute("answer", ans_dto);

        if(user != null){
            model.addAttribute("uid", user.getId());

            boolean isAdmin= false;
            if(user.getRole().equals("ROLE_ADMIN")) isAdmin = true;
            model.addAttribute("admin",isAdmin);

            model.addAttribute("admin_name",user.getName());

            boolean isUser = false;
            boolean isUserOrAdmin = false;
            if(dto.getUid().equals(user.getId())) isUser = true;
            if(dto.getUid().equals(user.getId()) || user.getRole().equals("ROLE_ADMIN")) isUserOrAdmin = true;
            model.addAttribute("user", isUser);
            model.addAttribute("UserOrAdmin", isUserOrAdmin);
        }

        return "question-detail";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "login";
    }

    @GetMapping("/cart")
    public String cart(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
            List<CartProductListResponseDto> cartList = cartService.findUserCart(user.getId());
            model.addAttribute("cartList", cartList);
        }

        return "cart";
    }

    @GetMapping("/sign-up")
    public String sign_up(){
        return "sign-up";
    }

    @GetMapping("/account")
    public String account_management(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
            model.addAttribute("user", user);
        }

        return "account";
    }

    @GetMapping("/find-password")
    public String find_password(){
        return "find-pwd";
    }

    @GetMapping("/apart-view")
    public String apart_view(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "apart-view";
    }

    @GetMapping("/area-info")
    public String area_info(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "area-info";
    }

    @GetMapping("/in-facility")
    public String in_facility(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "in-facility";
    }

    @GetMapping("/community-facility")
    public String community_facility(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "community-facility";
    }

    @GetMapping("/surrounding-location")
    public String surrounding_location(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "surrounding-location";
    }

    @GetMapping("/find-way")
    public String find_way(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }

        return "find-way";
    }

    @GetMapping("/administer/member-management")
    public String member_management(Model model, @LoginUser SessionUser user){
        if(user != null){
            model.addAttribute("uid", user.getId());
        }
        List<CustomUserListResponseDto> dto = userService.findAllDesc();
        model.addAttribute("userList", dto);

        return "member-management";
    }
}
