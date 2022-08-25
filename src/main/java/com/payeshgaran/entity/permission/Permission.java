package com.payeshgaran.entity.permission;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public enum Permission {
    READ_USER("user:read"),
    WRITE_USER("user:write"),
    READ_ACCOUNT("account:read"),
    WRITE_ACCOUNT("account:write");
//    private String ss;

    Permission(String s) {

    }
}
