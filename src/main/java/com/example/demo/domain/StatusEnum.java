package com.example.demo.domain;

import lombok.Getter;

@Getter
public enum StatusEnum {
    DELETED(0), NORMAL(1), DISABLED(2);

    private int id;

    StatusEnum(int id) {
        this.id = id;
    }
}
