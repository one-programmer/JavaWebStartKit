package com.example.demo.domain.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginUserVO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
