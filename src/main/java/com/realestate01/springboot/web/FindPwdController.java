package com.realestate01.springboot.web;

import com.realestate01.springboot.service.SendMailService;
import com.realestate01.springboot.service.UserService;
import com.realestate01.springboot.web.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class FindPwdController {
    final private UserService userService;
    final private SendMailService mailService;

    @GetMapping("/api/v0/find_pw")
    public Map<Object, Object> find_pw(String userEmail, String userName) {
        boolean check = userService.name_email_check(userEmail, userName);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("check", check);

        return map;
    }


    //등록된 이메일로 임시비밀번호를 발송하고 발송된 임시비밀번호로 사용자의 pw를 변경하는 컨트롤러
    @PostMapping("/api/v0/send_email")
    public void sendEmail(String userEmail, String userName){
        MailDto mailDto = mailService.createMailAndSendPassword(userEmail, userName);
        mailService.mailSend(mailDto);

    }
}
