package com.example.demo.service;

import com.example.demo.domain.vo.UserRegisterVO;
import com.example.demo.domain.vo.UserVO;

public interface AuthService {

    UserVO register(UserRegisterVO user);

    String login(String username, String password);

}
