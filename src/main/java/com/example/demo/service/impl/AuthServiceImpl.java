package com.example.demo.service.impl;

import com.example.demo.common.exception.DemoErrorEnum;
import com.example.demo.common.exception.DemoException;
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
    public UserVO register(UserVO user) {
        return null;
    }

    @Override
    public String login(String username, String password) {

        final User user = userRepository.findByUsername(username);

        // FIXME password verify so bad
        if (user == null || !user.getPassword().equals(password)) {
            throw new DemoException("用户名或密码错误", DemoErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        return jwtTokenUtil.generateToken(user);
    }
}
