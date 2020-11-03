package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.model.dto.login.LoginDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController{

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDto userLogin(@RequestBody LoginDto loginDto, HttpSession httpSession){
        return loginService.userLogin(loginDto, httpSession);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void userLogout(HttpSession httpSession){
        loginService.userLogout(httpSession);
    }


}
