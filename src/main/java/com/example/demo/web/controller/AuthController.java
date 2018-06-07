package com.example.demo.web.controller;

import com.example.demo.domain.vo.UserLoginVO;
import com.example.demo.domain.vo.UserRegisterVO;
import com.example.demo.domain.vo.UserVO;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserVO register(@RequestBody @Valid UserRegisterVO user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String auth(@RequestBody @Valid UserLoginVO user) {
        return authService.login(user.getUsername(), user.getPassword());
    }

}
