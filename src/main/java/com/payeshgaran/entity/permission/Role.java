package com.payeshgaran.entity.permission;

import java.util.HashSet;
import java.util.Set;

public enum Role {
    ADMIN
    , USER;

    private Set<Permission> permissions  = new HashSet<>();
}
