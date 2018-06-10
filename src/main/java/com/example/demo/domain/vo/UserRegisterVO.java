package com.example.demo.domain.vo;

import com.example.demo.common.utils.PasswordEncoder;
import com.example.demo.domain.po.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterVO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String mobile;

    public User covertToUser() {
        UserRegisterVOCovert userRegisterVOCovert = new UserRegisterVOCovert();
        return userRegisterVOCovert.doForward(this);
    }

    private static class UserRegisterVOCovert extends Converter<UserRegisterVO, User> {


        @Override
        protected User doForward(UserRegisterVO userRegisterVO) {
            User user = new User();
            BeanUtils.copyProperties(userRegisterVO, user);
            user.setPassword(new PasswordEncoder().encode(userRegisterVO.getPassword()));
            return user;
        }

        @Override
        protected UserRegisterVO doBackward(User user) {
            throw new UnsupportedOperationException();
        }
    }

}
