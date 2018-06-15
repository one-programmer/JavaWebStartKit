package com.example.demo.service.impl;

import com.example.demo.common.exception.DemoErrorEnum;
import com.example.demo.common.exception.DemoException;
import com.example.demo.common.utils.PasswordEncoder;
import com.example.demo.domain.vo.UserRegisterVO;
import com.example.demo.web.security.JwtTokenUtil;
import com.example.demo.domain.po.User;
import com.example.demo.domain.vo.UserVO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;

    private UserRepository userRepository;

    @Autowired
    public AuthServiceImpl(JwtTokenUtil jwtTokenUtil,
                           UserRepository userRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @Override
    public UserVO register(UserRegisterVO userRegisterVO) {
        if (userRepository.findByUsername(userRegisterVO.getUsername()) != null) {
            throw new DemoException("用户名已存在", DemoErrorEnum.ACCOUNT_ALREADY_EXISTS);
        }
        if (userRepository.findByMobile(userRegisterVO.getMobile()) != null) {
            throw new DemoException("手机号已注册", DemoErrorEnum.ACCOUNT_ALREADY_EXISTS);
        }

        User user = userRepository.save(userRegisterVO.covertToUser());
        return UserVO.converFor(user);
    }

    @Override
    public String login(String username, String password) {

        final User user = userRepository.findByUsername(username);

        if (user == null || !new PasswordEncoder().matches(password, user.getPassword())) {
            throw new DemoException("用户名或密码错误", DemoErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        return jwtTokenUtil.generateToken(user);
    }
}
