package com.example.demo.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginVO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
