package com.marketplace.project.entities.enums;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum RoleType {
    BUYER("BUYER"),
    SELLER("SELLER"),
    ADMIN("ADMIN");

    private String roleType;

    RoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
}
