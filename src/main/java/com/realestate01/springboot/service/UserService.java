package com.realestate01.springboot.service;

import com.realestate01.springboot.config.auth.dto.SessionUser;
import com.realestate01.springboot.domain.user.*;
import com.realestate01.springboot.web.dto.CustomUserDto;
import com.realestate01.springboot.web.dto.CustomUserListResponseDto;
import com.realestate01.springboot.web.dto.CustomUserUpdateDto;
import com.realestate01.springboot.web.dto.NoticeListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        return userRepository.findByEmailNotOauth(email)
                .orElseThrow(() -> new UsernameNotFoundException((email)));
    }

    public Long save(CustomUserDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(encoder.encode(dto.getPassword()));

        Role role = Role.USER;

        if(dto.getEmail().equals("leirion17@gmail.com")) role = Role.ADMIN;

        return userRepository.save(new User(dto.getEmail(), dto.getName(), dto.getPassword(), role)).getId();
    }

    public Long update(Long id, CustomUserUpdateDto dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        if(dto.getPassword() == null){
            user.update_name(dto.getName());
            return id;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(encoder.encode(dto.getPassword()));
        user.update_info(dto.getPassword(), dto.getName());
        httpSession.setAttribute("user",new SessionUser(user));

        return id;
    }

    public Boolean delete(Long id, String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        if(encoder.matches(password, user.getPassword())){
            userRepository.delete(user);
            SecurityContextHolder.clearContext();
            httpSession.invalidate();
            return true;
        }
        else return false;
    }

    public void oauth_delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        userRepository.delete(user);
        httpSession.invalidate();
    }

    public void member_delete(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        userRepository.delete(user);
    }

    public boolean id_check(String email){
        User user = userRepository.findByEmailNotOauth(email).orElse(null);
        if(user == null) return false;
        else return true;

    }

    public boolean name_email_check(String email, String name){
        User user = userRepository.findByEmailNotOauth(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다." ));
        if(user!=null && user.getName().equals(name)) return true;
        else return false;
    }

    public List<CustomUserListResponseDto> findAllDesc(){
        return userRepository.findAllDesc().stream()
                .map(CustomUserListResponseDto::new)
                .collect(Collectors.toList());
    }
}
