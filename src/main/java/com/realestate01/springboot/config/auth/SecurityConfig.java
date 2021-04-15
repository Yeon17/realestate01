package com.realestate01.springboot.config.auth;

import com.realestate01.springboot.config.auth.dto.SessionUser;
import com.realestate01.springboot.domain.user.Role;
import com.realestate01.springboot.domain.user.User;
import com.realestate01.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/img/**", "/js/**", "/h2-console/**","/vendor/**", "/profile").permitAll()
                .antMatchers("/api/v1/**","/enroll-question","/account").hasAnyRole(Role.USER.name(),Role.ADMIN.name())
                .antMatchers("/administer/**").hasRole(Role.ADMIN.name())
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .defaultSuccessUrl("/")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        HttpSession httpSession = request.getSession();
                        User user = (User)authentication.getPrincipal();
                        httpSession.setAttribute("user", new SessionUser(user.getId(), user.getEmail(), user.getName(),user.getRoleKey(), user.getPassword(), user.getOauth()));

                        response.sendRedirect("/");
                    }
                })
                .failureHandler(failureHandler())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new LoginFailHandler();
    }

}