package com.example.demo.domain.vo;

import com.example.demo.domain.po.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class UserVO {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String mobile;

    public static UserVO converFor(User user) {
        UserVOCovert userVOCovert = new UserVOCovert();
        return userVOCovert.reverse().convert(user);
    }

    private static class UserVOCovert extends Converter<UserVO, User> {
        @Override
        protected User doForward(UserVO userVO) {
            return null;
        }

        @Override
        protected UserVO doBackward(User user) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }
    }

}
