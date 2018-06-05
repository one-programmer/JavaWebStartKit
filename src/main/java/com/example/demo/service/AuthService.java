package com.example.demo.service;

import com.example.demo.domain.vo.UserVO;

public interface AuthService {

    UserVO register(UserVO user);

    String login(String username, String password);

}
