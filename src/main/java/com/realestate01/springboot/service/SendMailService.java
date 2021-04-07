package com.realestate01.springboot.service;

import com.realestate01.springboot.domain.user.User;
import com.realestate01.springboot.domain.user.UserRepository;
import com.realestate01.springboot.web.dto.MailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMailService {
    private final UserRepository userRepository;

    private final JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "leirion17@gmail.com";

    @Transactional
    public MailDto createMailAndSendPassword(String userEmail, String userName){
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(userEmail);
        dto.setTitle(userName+"님의 단지내 동천파크자이 공인중개사 사무소 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. 단지내 동천파크자이 공인중개사 사무소 임시비밀번호 안내 관련 이메일 입니다. "  + userName + "님의 임시 비밀번호는 "
                + str + " 입니다.");
        updatePassword(str,userEmail);
        return dto;
    }

    public void updatePassword(String str,String userEmail){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByEmailNotOauth(userEmail).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다." ));

        user.update_pwd(encoder.encode(str));
    }


    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    public void mailSend(MailDto mailDto){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(SendMailService.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());

        mailSender.send(message);
    }

}
